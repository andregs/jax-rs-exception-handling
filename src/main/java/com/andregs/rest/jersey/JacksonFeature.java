package com.andregs.rest.jersey;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.internal.InternalProperties;
import org.glassfish.jersey.internal.util.PropertiesHelper;
import org.glassfish.jersey.jackson.internal.FilteringJacksonJaxbJsonProvider;
import org.glassfish.jersey.jackson.internal.JacksonFilteringFeature;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.glassfish.jersey.message.filtering.EntityFilteringFeature;

/**
 * Register JacksonFeature without registering its exception mappers.
 * That's my approach to workaroud https://stackoverflow.com/questions/45478512/
 * 
 * @see org.glassfish.jersey.jackson.JacksonFeature
 * @see https://github.com/FasterXML/jackson-jaxrs-providers/issues/22
 */
@Provider
public class JacksonFeature implements Feature {

    private static final Logger LOG = Logger.getLogger(JacksonFeature.class.getName());

    private static final String JSON_FEATURE = JacksonFeature.class.getSimpleName();

    @Override
    public boolean configure(FeatureContext context) {
        LOG.info("Configuring our custom implementation of JacksonFeature");

        final Configuration config = context.getConfiguration();

        // Disable other JSON providers.
        context.property(PropertiesHelper.getPropertyNameForRuntime(InternalProperties.JSON_FEATURE, config.getRuntimeType()),
                JSON_FEATURE);

        // Register Jackson.
        if (!config.isRegistered(JacksonJaxbJsonProvider.class)) {
            boolean enabled = EntityFilteringFeature.enabled(config);

            LOG.log(Level.FINE, "Entity filtering enabled? {0}", enabled);
            if (enabled) {
                context.register(JacksonFilteringFeature.class);
                context.register(FilteringJacksonJaxbJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);
            } else {
                context.register(JacksonJaxbJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);
            }
        }

        return true;
    }

}
