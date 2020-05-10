package net.yosifov.filipov.training.accounting.acc20;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

//    public ApiInfo(String title,
//                   String description,
//                   String version,
//                   String termsOfServiceUrl,
//                   Contact contact,
//                   String license,
//                   String licenseUrl,
//                   Collection<VendorExtension> vendorExtensions) {
//    }

    public static final Contact DEFAULT_CONTACT = new Contact(
            "Strashimir Yosifov", "https://github.com/syosifov/project-acc-20", "syosifov@gmail.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "ACC-20",
            "An experimental Project",
            "0.0.1-SNAPSHOT",
            "urn:tos",
            DEFAULT_CONTACT,
            "MIT",
            "https://github.com/syosifov/project-acc-20/blob/master/LICENSE.txt",
            new ArrayList<VendorExtension>());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<String>(Arrays.asList("application/json"
            //        ,"application/xml"
            ));

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);

    }
}
