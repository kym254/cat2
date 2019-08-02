package com.test.demo;

import com.bbit.model.University;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.xml.stream.Location;
import javax.xml.ws.Response;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Component
public class RestTesting  implements CommandLineRunner {
    @Override
    public  void run(String...args)throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<com.bbit.model.University>>response = restTemplate.exchange(
                "http://10.51.10.111:8080/universities", HttpMethod.GET, null,

        new ParameterizedTypeReference<List<com.bbit.model.University>>(){});
        List<com.bbit.model.University>universities=response.getBody();

        for (com.bbit.model.University university: universities){
            System.out.println(university.toString());
        }
        System.out.println("FIND One(GET)--------------------------------");
        com.bbit.model.University university = restTemplate.getForObject("http://10.51.10.111:8080/universities/2", com.bbit.model.University.class);
System.out.println((university.toString()));

System.out.println("Creating(POST)----------------------------------");

        com.bbit.model.University newUniversity = new University("tum", "msa",700);
        University createUniveristy = restTemplate.postForObject( "http://10.51.10.111:8080/universities/", newUniversity,University.class);
System.out.println(createUniveristy.toString());

String createCourseUrl="http://10.51.10.111:8080/universities/"+createUniveristy.getId()+"/courses";
 Course newCourse = new Course("marine eng");
 Course createdCourse = restTemplate.postForObject(createCourseUrl,newCourse ,Course.class);
 System.out.println(createdCourse.toString());
    }

}
