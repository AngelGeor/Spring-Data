package com.example.modelmapperlab;

import com.example.modelmapperlab.entities.dto.AddressXmlDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@Component
public class XmlTestMain implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        AddressXmlDto xmlDto = new AddressXmlDto(5, "Bulgaria", "Pleven");

        JAXBContext jaxbContext = JAXBContext.newInstance(AddressXmlDto.class);

        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);

        marshaller.marshal(xmlDto,System.out);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        AddressXmlDto unmarshall = (AddressXmlDto) unmarshaller.unmarshal(System.in);

        System.out.println(unmarshall);
    }
}
