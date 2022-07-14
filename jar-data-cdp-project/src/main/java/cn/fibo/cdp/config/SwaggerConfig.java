

package cn.fibo.cdp.config;

import cn.fibo.cdp.common.enums.RtnCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableOpenApi
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket createRestApi() {
        List<Response> responseMessageList = new ArrayList<>();
        Arrays.stream(RtnCode.values()).forEach(errorEnums -> {
            responseMessageList.add(
                    new ResponseBuilder().code(errorEnums.getCode().toString()).description(errorEnums.getMsg()).build()
            );
        });
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET,responseMessageList)
                .globalResponses(HttpMethod.DELETE,responseMessageList)
                .globalResponses(HttpMethod.POST,responseMessageList)
            .apiInfo(apiInfo())
            .select()

            //加了ApiOperation注解的类，才生成接口文档
            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
            //包下的类，才生成接口文档
            //.apis(RequestHandlerSelectors.basePackage("cn.fibo.cdp"))
            .paths(PathSelectors.any())
            .build()
            .securitySchemes(security());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("斐波那契")
            .description("FIBO-CDP文档")
            .termsOfServiceUrl("https://www.fibo.cn/")
            .version("3.0.0")
            .build();
    }

    private List<SecurityScheme> security() {
        return newArrayList(
            new ApiKey("token", "token", "header")
        );
    }

}
