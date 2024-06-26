# Java And Spring Coding Just For Fun 😁

My Java and Spring Coding playground Repository.<br>
_nothing special_. **_just playing with code_**, that's all.

> ~~Remark : Please note that all code is written based on JDK 17. Please keep this in mind.~~<br>
> Remark : (2024-03-31) The Code is now based on `Java 21`<br>
> Remark : (2024-03-31) The Spring Boot Version is now `3`

<br><br>

## 🧭 Coding History

Here is a list of tasks that I have done so far.

- DataUri ⇿ Image File Converting
  - [data uri and file converting in two seperate method](src/test/java/coding/toast/bread/data_uri/DataUriTest.java)
  - [data uri and file converting in one method](src/test/java/coding/toast/bread/data_uri/DataUriAndFileConvertingTest.java)

<br>

- Controlling and manipulating strings
  - [filtering string list with prefix pattern](src/test/java/coding/toast/bread/string_control/FilteringStringListTest.java)
  - [converting between CamelCase and snake_case](src/test/java/coding/toast/bread/string_control/CamelCaseSnakeCaseExchangeTest.java)
  - [string reverse](src/test/java/coding/toast/bread/string_control/ReversingStringOrderTest.java)
  - [test spring's PropertyPlaceHolderHelper](src/test/java/coding/toast/bread/string_control/PropertyPlaceholderHelperTest.java)

<br>

- XML Marshalling, UnMarshalling
  - [(Jakarta) JAXB - marshalling, unmarshalling](src/test/java/coding/toast/bread/xml_pojo_convert/XmlPojoConvertTest.java)
    - this code need some artifacts from maven repository. Please refer to the [pom.xml file](https://github.com/CodingToastBread/java-playground/blob/main/pom.xml)!
    - required artifacts: `jakarta.xml.bind-api`,  `jakarta.activation-api` `jaxb-runtime`
  - [Using Jackson XmlMapper (+ class)](src/test/java/coding/toast/bread/converting/JacksonXmlToPojoConvertTests.java)
  - [Using Jackson XmlMapper (+ record)](src/test/java/coding/toast/bread/converting/JacksonXmlToPojoConvertWithRecordTests.java)

<br>

- File System Controlling
  - [playing with File & Directory](src/test/java/coding/toast/bread/playing_with_file/JavaFilesApiTest.java)

<br>

- Http Client API
  - [playing with Java HttpClient](src/test/java/coding/toast/bread/http_client_api/JavaHttpClientTests.java)
  - [download video with Java HttpClient](src/test/java/coding/toast/bread/http_client_api/JavaHttpClientDownLoadFileTests.java)
  - [HttpUrlConnection Practice](src/test/java/coding/toast/bread/http_client_api/JavaHttpUrlConnectionTests.java)
  - [RestTemplate Practice](src/test/java/coding/toast/bread/http_client_api/RestTemplateTests.java)
  - [Sending 100 requests at once](src/test/java/coding/toast/bread/http_client_api/SendingManyRequestAtOnceTest.java)

<br>

- Proxy API
  - [java built-in proxy test](src/test/java/coding/toast/bread/proxy/JdkProxyTests.java) 
  - [how to create CGLIB PROXY](src/test/java/coding/toast/bread/proxy/CglibProxyTests.java)
  - [how to use ProxyFactory](src/test/java/coding/toast/bread/proxy/ProxyFactoryTests.java)

<br>

- Converting
  - [Convert Test Using Jackson ObjectMapper](src/test/java/coding/toast/bread/converting/JacksonConvertTests.java) 
  - [Copy values using Model Mapper](src/test/java/coding/toast/bread/converting/ModelMapperConvertTests.java)
  - [Convert To Pojo <-> CSV](src/test/java/coding/toast/bread/converting/JacksonCsvPojoConvertTests.java)
  - [Convert To Pojo that looks similar with Excel Format](src/test/java/coding/toast/bread/converting/ExcelLikeJsonToPojoTests.java)

<br>

- Spring-related code 
  - [Simple SpEL test](src/test/java/coding/toast/bread/spring/spel/SpringExpressionLangTests.java) 

<br>

