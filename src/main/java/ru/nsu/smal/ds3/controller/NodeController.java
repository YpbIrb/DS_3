package ru.nsu.smal.ds3.controller;


import org.springframework.web.bind.annotation.*;
import ru.nsu.smal.ds3.dao.NodeDTO;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import ru.nsu.smal.ds3.service.NodeService;
import ru.nsu.smal.ds3.utility.XMLProcessor;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/osm")
public class NodeController {

    private final NodeService nodeService;
    private final XMLProcessor xmlProcessor;


    private static Logger logger = LogManager.getLogger(NodeController.class);

    public NodeController(NodeService nodeService, XMLProcessor xmlProcessor) {
        this.nodeService = nodeService;
        this.xmlProcessor = xmlProcessor;
    }

    @PostMapping(value = "/node/download_xml")
    public void downloadXML(){
        File bz2File = new File("RU-NVS.osm.bz2");


        try (InputStream bzIn = new BZip2CompressorInputStream(new BufferedInputStream(new FileInputStream(bz2File)))) {
            XMLInputFactory factory = XMLInputFactory.newFactory();
            XMLStreamReader xmlStreamReader = factory.createXMLStreamReader(bzIn);
            xmlProcessor.ProcessOSM(xmlStreamReader);
            
        } catch (XMLStreamException e) {
            logger.error("XMLStreamException in xml processing");
            e.printStackTrace();
        } catch (JAXBException e) {
            logger.error("JAXBException in xml processing");
            e.printStackTrace();
        } catch (SQLException e) {
            logger.error("SQLException in xml processing");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException in xml processing");
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("IOException in xml processing");
            e.printStackTrace();
        }

    }

    @PostMapping(value = "/node/create")
    public ResponseEntity<NodeDTO> createNode(@RequestBody HttpEntity<NodeDTO> nodeDTOHttpEntity){

        logger.info("Adding node with id : " + nodeDTOHttpEntity.getBody().getId());

        return ResponseEntity.ok(nodeService.createNode(nodeDTOHttpEntity.getBody()));
    }

    @GetMapping(value = "/node/get/{id}")
    public ResponseEntity<NodeDTO> getNode(@PathVariable ("id") BigInteger nodeId) {
        return ResponseEntity.ok(nodeService.getNode(nodeId));
    }

    @DeleteMapping(value = "/node/delete/{id}")
    public void deleteNode(@PathVariable ("id") BigInteger nodeId) {
        nodeService.deleteNode(nodeId);
    }

    @PostMapping(value = "/node/update/{id}")
    public ResponseEntity<NodeDTO> updateNode(@PathVariable ("id") BigInteger nodeId, @RequestBody HttpEntity<NodeDTO> nodeDTOHttpEntity) {
        return ResponseEntity.ok(nodeService.updateNode(nodeId, nodeDTOHttpEntity.getBody()));
    }

    @GetMapping(value = "node/find_near")
    public ResponseEntity<List<NodeDTO>> getNodesByRadius (@RequestParam("lon") Double lon, @RequestParam("lat") Double lat, @RequestParam("radius") Double radius){
        return ResponseEntity.ok(nodeService.getNodesByRadius(lon, lat, radius));
    }
}
