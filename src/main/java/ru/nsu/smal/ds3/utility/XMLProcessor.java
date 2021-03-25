package ru.nsu.smal.ds3.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.nsu.smal.ds3.osm.model.generated.Node;
import ru.nsu.smal.ds3.service.INodeService;
import ru.nsu.smal.ds3.service.NodeService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.StreamReaderDelegate;
import java.sql.SQLException;


@Service
public class XMLProcessor {

    private static Logger logger = LogManager.getLogger(XMLProcessor.class);
    private final INodeService nodeService;

    public XMLProcessor(INodeService nodeService) {
        this.nodeService = nodeService;
    }

    public void ProcessOSM(XMLStreamReader xmlReader) throws XMLStreamException, JAXBException, SQLException {

        XMLStreamReader xsr;
        xsr = new XsiTypeReader(xmlReader);


        JAXBContext jc = JAXBContext.newInstance(Node.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();

        xsr.nextTag();
        while (! xsr.getLocalName().equals("node"))
            xsr.nextTag();                               //skip all elements before nodes

        int n = 0;
        int last_logged_node_num = 0;
        Object object;
        Node node;

        while(xsr.getLocalName().equals("node") && xsr.getEventType() == XMLStreamConstants.START_ELEMENT){
            object = unmarshaller.unmarshal(xsr);
            node =  (Node)object;

            n++;

            if (n == (last_logged_node_num + 1000000)){
                last_logged_node_num = n;
                logger.info("Done with " + last_logged_node_num + " node");
            }

            nodeService.createNode(Converter.NodeToDTO(node));

            xsr.nextTag();
        }

        logger.info("Finish on " + last_logged_node_num + " node");
    }

    //Кастомный ридер для того, чтобы можно было парсить xml, в которой пропущены пространства имен
    private static class XsiTypeReader extends StreamReaderDelegate {

        public XsiTypeReader(XMLStreamReader reader) {
            super(reader);
        }

        @Override
        public String getNamespacePrefix(int arg0) {
            return "http://openstreetmap.org/osm/0.6";
        }

        @Override
        public String getNamespaceURI(int arg0) {
            return "http://openstreetmap.org/osm/0.6";
        }

        @Override
        public String getNamespaceURI() {
            return "http://openstreetmap.org/osm/0.6";
        }


        @Override
        public String getNamespaceURI(String prefix) {
            return "http://openstreetmap.org/osm/0.6";
        }

    }

}
