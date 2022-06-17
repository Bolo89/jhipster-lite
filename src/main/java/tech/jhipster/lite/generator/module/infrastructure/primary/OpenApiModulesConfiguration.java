package tech.jhipster.lite.generator.module.infrastructure.primary;

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.common.domain.Enums;
import tech.jhipster.lite.generator.module.domain.JHipsterModuleSlug;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModulePropertyDefinition;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterPropertyDescription;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterPropertyExample;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterPropertyKey;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterPropertyType;

@Configuration
class OpenApiModulesConfiguration {

  private static final String STRING_TYPE = "string";
  private static final String OBJECT_TYPE = "object";
  private static final String MODULE_PROPERTY_DEFINITION_SCHEMA_NAME = "JHipsterModulePropertiesDefinition";
  private static final String MODULE_PROPERTIES_DEFINITION_SCHEMA_NAME = "JHipsterModulePropertyDefinition";

  private static final Schema<?> PROJECT_DTO_SCHEMA = new Schema<>().$ref("#/components/schemas/ProjectDTO");
  private static final Schema<?> MODULE_PROPERTY_DEFINITION_SCHEMA = new Schema<>()
    .$ref("#/components/schemas/" + MODULE_PROPERTY_DEFINITION_SCHEMA_NAME);
  private static final Schema<?> MODULE_PROPERTIES_DEFINITION_SCHEMA = new Schema<>()
    .$ref("#/components/schemas/" + MODULE_PROPERTIES_DEFINITION_SCHEMA_NAME);

  private static final String JSON_MEDIA_TYPE = "application/json";

  @Bean
  OpenApiCustomiser openApiModules(JHipsterModulesResources modules) {
    return openApi -> {
      openApi
        .schema(MODULE_PROPERTIES_DEFINITION_SCHEMA_NAME, modulePropertyDefinitionSchema())
        .schema(MODULE_PROPERTY_DEFINITION_SCHEMA_NAME, propertiesDefinitionSchema());

      openApi.getComponents().getSchemas().putAll(moduleApplicationSchemas(modules));

      openApi.getPaths().putAll(buildJHipsterModulesPaths(modules));
    };
  }

  @SuppressWarnings("unchecked")
  private Schema<?> propertiesDefinitionSchema() {
    return new Schema<>()
      .addProperty(
        "definitions",
        new Schema<>().type("array").description("Definitions for properties in this module").items(MODULE_PROPERTIES_DEFINITION_SCHEMA)
      )
      .description("Definitions for properties in this module")
      .type(OBJECT_TYPE);
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  private Schema modulePropertyDefinitionSchema() {
    return new Schema<>()
      .name(MODULE_PROPERTIES_DEFINITION_SCHEMA_NAME)
      .type(OBJECT_TYPE)
      .addProperty(
        "type",
        new Schema<>()
          .type(STRING_TYPE)
          ._enum(Stream.of(JHipsterPropertyType.values()).map(JHipsterPropertyType::name).toList())
          .description("Type of this property")
      )
      .addProperty("mandatory", new Schema<>().type("boolean").description("True if the field is mandatory, false otherwise"))
      .addProperty("key", new Schema<>().type(STRING_TYPE).description("Key of this property"))
      .addProperty("description", new Schema<>().type(STRING_TYPE).description("Description of this property"))
      .addProperty("example", new Schema<>().type(STRING_TYPE).description("Example value for this property"))
      .required(List.of("type", "mandatory", "key"));
  }

  private Map<String, Schema<?>> moduleApplicationSchemas(JHipsterModulesResources modules) {
    return modules.stream().collect(Collectors.toMap(module -> schemaName(module.slug()), toModucleApplicationSchema()));
  }

  private Function<JHipsterModuleResource, Schema<?>> toModucleApplicationSchema() {
    return module -> {
      Schema<?> schema = new Schema<>()
        .name(schemaName(module.slug()))
        .type(OBJECT_TYPE)
        .addProperty("projectFolder", new Schema<>().type(STRING_TYPE).description("Path to the project"));

      appendPropertiesDefinition(module, schema);

      return schema.required(buildRequirements(module));
    };
  }

  private void appendPropertiesDefinition(JHipsterModuleResource module, Schema<?> schema) {
    @SuppressWarnings("rawtypes")
    Map<String, Schema> moduleProperties = moduleProperties(module);

    if (moduleProperties.isEmpty()) {
      return;
    }

    Schema<?> modulePropertiesSchema = new Schema<>().type(OBJECT_TYPE).description("Properties for this module");
    modulePropertiesSchema.setProperties(moduleProperties);
    schema.addProperty("properties", modulePropertiesSchema);
  }

  private List<String> buildRequirements(JHipsterModuleResource module) {
    return Stream
      .concat(
        Stream.of("projectFolder"),
        module
          .propertiesDefinition()
          .stream()
          .filter(JHipsterModulePropertyDefinition::isMandatory)
          .map(JHipsterModulePropertyDefinition::key)
          .map(JHipsterPropertyKey::get)
      )
      .toList();
  }

  @SuppressWarnings("rawtypes")
  private Map<String, Schema> moduleProperties(JHipsterModuleResource module) {
    return module.propertiesDefinition().stream().collect(Collectors.toMap(property -> property.key().get(), toPropertySchema()));
  }

  private Function<JHipsterModulePropertyDefinition, Schema<?>> toPropertySchema() {
    return property ->
      new Schema<>()
        .type(Enums.map(property.type(), OpenApiFieldType.class).key())
        .description(property.description().map(JHipsterPropertyDescription::get).orElse(null))
        .example(property.example().map(JHipsterPropertyExample::get).orElse(null));
  }

  private Paths buildJHipsterModulesPaths(JHipsterModulesResources modules) {
    Paths paths = new Paths();

    paths.putAll(legacyModules(modules));
    paths.putAll(modulesPropertiesDefinitions(modules));
    paths.putAll(modulesApplications(modules));

    return paths;
  }

  private Map<String, PathItem> legacyModules(JHipsterModulesResources modules) {
    return modules.stream().collect(Collectors.toMap(JHipsterModuleResource::legacyUrl, module -> legacyModuleApiDoc(module.apiDoc())));
  }

  private PathItem legacyModuleApiDoc(JHipsterModuleApiDoc apiDoc) {
    RequestBody requestBody = new RequestBody()
      .required(true)
      .content(new Content().addMediaType(JSON_MEDIA_TYPE, new MediaType().schema(PROJECT_DTO_SCHEMA)));

    Operation postOperation = new Operation().summary(apiDoc.operation()).tags(List.of(apiDoc.tag())).requestBody(requestBody);

    return new PathItem().post(postOperation);
  }

  private Map<String, PathItem> modulesPropertiesDefinitions(JHipsterModulesResources modules) {
    return modules
      .stream()
      .collect(Collectors.toMap(JHipsterModuleResource::moduleUrl, module -> modulePropertiesDefinition(module.apiDoc(), module.slug())));
  }

  private PathItem modulePropertiesDefinition(JHipsterModuleApiDoc apiDoc, JHipsterModuleSlug slug) {
    Operation getOpetation = new Operation()
      .operationId(slug.get() + "-properties-definition")
      .summary("Get " + slug.get() + " properties definitions")
      .tags(List.of(apiDoc.tag()))
      .responses(
        new ApiResponses()
          .addApiResponse(
            "200",
            new ApiResponse()
              .description("Definition for this module properties")
              .content(new Content().addMediaType("*/*", new MediaType().schema(MODULE_PROPERTY_DEFINITION_SCHEMA)))
          )
      );

    return new PathItem().get(getOpetation);
  }

  private Map<String, PathItem> modulesApplications(JHipsterModulesResources modules) {
    return modules
      .stream()
      .collect(
        Collectors.toMap(module -> module.moduleUrl() + "/apply", module -> moduleApplicationDefinition(module.apiDoc(), module.slug()))
      );
  }

  private PathItem moduleApplicationDefinition(JHipsterModuleApiDoc apiDoc, JHipsterModuleSlug slug) {
    Operation postOperation = new Operation()
      .operationId(slug.get() + "-application")
      .summary(apiDoc.operation())
      .tags(List.of(apiDoc.tag()))
      .requestBody(
        new RequestBody()
          .required(true)
          .content(new Content().addMediaType(JSON_MEDIA_TYPE, new MediaType().schema(new Schema<>().$ref(schemaName(slug)))))
      );

    return new PathItem().post(postOperation);
  }

  private String schemaName(JHipsterModuleSlug slug) {
    return slug.get() + "-schema";
  }
}