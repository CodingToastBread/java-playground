package coding.toast.bread.converting;

import coding.toast.bread.converting.dto.EmployeeDTO;
import coding.toast.bread.converting.vo.Address;
import coding.toast.bread.converting.vo.EmployeeVO;
import coding.toast.bread.converting.vo.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

/**
 * This is a Junit Test class that tests various conversion operations using the Jackson library
 */
public class JacksonConvertTests {
	
	static ObjectMapper mapper = new ObjectMapper()
		.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
		.configure(SerializationFeature.INDENT_OUTPUT, true);
	
	@Test
	@DisplayName("convert map <-> pojo")
	void pojoMapConvertTest() {
		Address address = new Address("korea", "seoul", "22322");
		
		// Are you curious about why we use TypeReference?
		// Then please search for 'Super Type Token' to learn more!
		Map<String, String> map = mapper.convertValue(address, new TypeReference<>() {
		});
		
		// test map size, and every entry.
		assertThat(map).hasSize(3);
		assertThat(map).contains(
			entry("region", "korea"),
			entry("city", "seoul"),
			entry("zipcode", "22322")
		);
		
		// re-convert to Address record and compare with the address obj
		Address pojo = mapper.convertValue(map, Address.class);
		assertThat(pojo).isEqualTo(address);
		
	}
	
	@Test
	@DisplayName("convert Json String To Pojo")
	void JsonStringToPojoTest() throws JsonProcessingException {
		String jsonString = """
			[
				{
					"name": "Coding Bread",
					"age" : 19
				},
				{
					"name": "Toast Bread",
					"age" : 23
				},
				{
					"name": "Yummy Bread",
					"age" : 27
				}
			]\
			""";
		System.out.println("jsonString = " + jsonString);
		
		List<Map<String, Object>> listOfMap = mapper.readValue(jsonString, new TypeReference<>() {});
		System.out.println(listOfMap);
		
		assertThat(listOfMap).hasSize(3);
		assertThat(listOfMap.get(0)).containsExactly(entry("name", "Coding Bread"), entry("age", 19));
		assertThat(listOfMap.get(1)).containsExactly(entry("name", "Toast Bread"), entry("age", 23));
		assertThat(listOfMap.get(2)).containsExactly(entry("name", "Yummy Bread"), entry("age", 27));
		
	}
	
	@Test
	@DisplayName("create Jackson JsonNode With JsonNodeFactory")
	void JsonFactoryTest() {
		JsonNodeFactory factory = JsonNodeFactory.instance;
		
		ObjectNode objectNode = factory.objectNode();
		objectNode.put("name", "toast bread");
		objectNode.put("age", 21);
		
		ArrayNode favoriteFood = factory.arrayNode().add("pizza").add("hamburger");
		objectNode.set("favoriteFood", favoriteFood);
		
		Person person = mapper.convertValue(objectNode, Person.class);
		// Person[name=toast bread, age=21, favoriteFood=[pizza, hamburger]]
		
		assertThat(person).isEqualTo(new Person("toast bread", 21, List.of("pizza", "hamburger")));
	}
	

	
	

	
	
	@Test
	@DisplayName("DTO -> VO, VO -> DTO")
	void DtoToVoAndVodToDTOTest() throws JsonProcessingException {
		
		EmployeeDTO dtoObject = EmployeeDTO.builder().empId(1L).name("toast bread").build();
		
		EmployeeVO voObject = new EmployeeVO(1L, "toast bread");
		
		EmployeeVO employeeVO = mapper.convertValue(dtoObject, EmployeeVO.class);
		
		EmployeeDTO employeeDTO = mapper.convertValue(voObject, EmployeeDTO.class);
		System.out.println("employeeDTO = " + employeeDTO);
		
		
		String s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(employeeVO);
		System.out.println("s = " + s);
		
	}
	
	
	
	@Test
	@DisplayName("put some data in json array")
	void dataInsertInJsonArrayTest() throws JsonProcessingException {
		String jsonString =  """
        {
            "detailFields":[
               {
                  "column":"area",
                  "comment":"area"
               },
               {
                  "column":"data",
                  "comment":"data"
               },
               {
                  "column":"year",
                  "comment":"year"
               }
            ],
            "searchCndFields":[
               {
                  "column":"data",
                  "comment":"data",
                  "groupCode":"CODE001"
               },
               {
                  "column":"year",
                  "comment":"year",
                  "groupCode":"CODE002"
               }
            ],
            "searchFields":[
               {
                  "column":"area",
                  "comment":"area"
               },
               {
                  "column":"data",
                  "comment":"data"
               },
               {
                  "column":"year",
                  "comment":"year"
               }
            ],
            "detailRepField":{
               "column":"area",
               "comment":"area"
            }
         }
        """;
		
		ObjectNode jsonNode = mapper.readValue(jsonString, new TypeReference<>() {});
		
		System.out.println("jsonNode.toPrettyString() = " + jsonNode.toPrettyString());
		
		JsonNode detailFields = jsonNode.path("detailFields");
		System.out.println("detailFields.toPrettyString() = " + detailFields.toPrettyString());
		
		if(detailFields.isArray()) {
			ArrayNode arrayNode = (ArrayNode) detailFields;
			for (JsonNode node : arrayNode) {
				
				Map<String, Object> groupCode = Map.of(
					"groupCode", "CODE001",
					"detailCodeList", List.of(
						Map.of(
							"detailNm", "areaName",
							"detailCode", "DETAIL_CODE001"
						),
						Map.of(
							"detailNm", "city",
							"detailCode", "DETAIL_CODE002"
						)
					)
				);
				
				((ObjectNode) node).setAll(mapper.convertValue(groupCode, ObjectNode.class));
				
			}
		}
		System.out.println("jsonNode.toPrettyString() = " + jsonNode.get("detailFields").toPrettyString());
	}
	
}
