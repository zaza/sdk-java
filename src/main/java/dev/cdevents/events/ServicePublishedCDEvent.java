package dev.cdevents.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.cdevents.constants.CDEventConstants;
import dev.cdevents.models.CDEvent;
import dev.cdevents.models.ServicePublishedSubject;

import java.net.URI;

public class ServicePublishedCDEvent extends CDEvent {

    private static final String CDEVENT_VERSION = "0.1.0";
    @JsonProperty(required = true)
    private ServicePublishedSubject subject;

    /**
     * Constructor to init CDEvent and set the Subject for {@link ServicePublishedCDEvent}.
     */
    public ServicePublishedCDEvent() {
        initCDEvent(currentCDEventType());
        setSubject(new ServicePublishedSubject(CDEventConstants.SubjectType.SERVICE));
    }

    /**
     * @return subject
     */
    public ServicePublishedSubject getSubject() {
        return subject;
    }

    /**
     * @param subject
     */
    public void setSubject(ServicePublishedSubject subject) {
        this.subject = subject;
    }

    /**
     * @return the current CDEvent Type
     */
    @Override
    public String currentCDEventType() {
        return CDEventConstants.CDEventTypes.ServicePublishedEvent.getEventType().concat(CDEVENT_VERSION);
    }

    /**
     * @return the service-Published-event schema URL
     */
    @Override
    public String schemaURL() {
        return String.format("https://cdevents.dev/%s/schema/service-published-event", CDEventConstants.CDEVENTS_SPEC_VERSION);
    }

    /**
     * @return the service-Published-event schema Json
     */
    @Override
    public String eventSchema() {
        return "{\n" +
                "  \"$schema\": \"https://json-schema.org/draft/2020-12/schema\",\n" +
                "  \"$id\": \"https://cdevents.dev/0.1.2/schema/service-published-event\",\n" +
                "  \"properties\": {\n" +
                "    \"context\": {\n" +
                "      \"properties\": {\n" +
                "        \"version\": {\n" +
                "          \"type\": \"string\",\n" +
                "          \"minLength\": 1\n" +
                "        },\n" +
                "        \"id\": {\n" +
                "          \"type\": \"string\",\n" +
                "          \"minLength\": 1\n" +
                "        },\n" +
                "        \"source\": {\n" +
                "          \"type\": \"string\",\n" +
                "          \"minLength\": 1\n" +
                "        },\n" +
                "        \"type\": {\n" +
                "          \"type\": \"string\",\n" +
                "          \"enum\": [\n" +
                "            \"dev.cdevents.service.published.0.1.0\"\n" +
                "          ],\n" +
                "          \"default\": \"dev.cdevents.service.published.0.1.0\"\n" +
                "        },\n" +
                "        \"timestamp\": {\n" +
                "          \"type\": \"string\",\n" +
                "          \"format\": \"date-time\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"additionalProperties\": false,\n" +
                "      \"type\": \"object\",\n" +
                "      \"required\": [\n" +
                "        \"version\",\n" +
                "        \"id\",\n" +
                "        \"source\",\n" +
                "        \"type\",\n" +
                "        \"timestamp\"\n" +
                "      ]\n" +
                "    },\n" +
                "    \"subject\": {\n" +
                "      \"properties\": {\n" +
                "        \"id\": {\n" +
                "          \"type\": \"string\",\n" +
                "          \"minLength\": 1\n" +
                "        },\n" +
                "        \"source\": {\n" +
                "          \"type\": \"string\"\n" +
                "        },\n" +
                "        \"type\": {\n" +
                "          \"type\": \"string\",\n" +
                "          \"minLength\": 1\n" +
                "        },\n" +
                "        \"content\": {\n" +
                "          \"properties\": {\n" +
                "            \"environment\": {\n" +
                "              \"properties\": {\n" +
                "                \"id\": {\n" +
                "                  \"type\": \"string\",\n" +
                "                  \"minLength\": 1\n" +
                "                },\n" +
                "                \"source\": {\n" +
                "                  \"type\": \"string\"\n" +
                "                }\n" +
                "              },\n" +
                "              \"additionalProperties\": false,\n" +
                "              \"type\": \"object\",\n" +
                "              \"required\": [\n" +
                "                \"id\"\n" +
                "              ]\n" +
                "            }\n" +
                "          },\n" +
                "          \"additionalProperties\": false,\n" +
                "          \"type\": \"object\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"additionalProperties\": false,\n" +
                "      \"type\": \"object\",\n" +
                "      \"required\": [\n" +
                "        \"id\",\n" +
                "        \"type\",\n" +
                "        \"content\"\n" +
                "      ]\n" +
                "    },\n" +
                "    \"customData\": {\n" +
                "      \"oneOf\": [\n" +
                "        {\n" +
                "          \"type\": \"object\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"string\",\n" +
                "          \"contentEncoding\": \"base64\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    \"customDataContentType\": {\n" +
                "      \"type\": \"string\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"additionalProperties\": false,\n" +
                "  \"type\": \"object\",\n" +
                "  \"required\": [\n" +
                "    \"context\",\n" +
                "    \"subject\"\n" +
                "  ]\n" +
                "}";
    }

    /**
     * @param subjectId
     * sets the subject Id
     */
    public void setSubjectId(String subjectId) {
        getSubject().setId(subjectId);
    }

    /**
     * @param subjectSource
     * sets the subject source
     */
    public void setSubjectSource(URI subjectSource) {
        getSubject().setSource(subjectSource);
    }

    /**
     * @param environmentId
     * sets the environmentId that this service belongs to
     */
    public void setSubjectEnvironmentId(String environmentId) {
        getSubject().getContent().getEnvironment().setId(environmentId);
    }

    /**
     * @param environmentSource
     * sets the environmentSource that this service belongs to
     */
    public void setSubjectEnvironmentSource(URI environmentSource) {
        getSubject().getContent().getEnvironment().setSource(environmentSource);
    }
}
