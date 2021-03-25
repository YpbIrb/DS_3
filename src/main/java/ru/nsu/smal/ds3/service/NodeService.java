package ru.nsu.smal.ds3.service;


import ru.nsu.smal.ds3.dao.NodeDTO;
import ru.nsu.smal.ds3.entity.NodeEntity;
import org.springframework.stereotype.Service;
import ru.nsu.smal.ds3.exception.NodeNotFoundException;
import ru.nsu.smal.ds3.repository.NodeRepository;
import ru.nsu.smal.ds3.utility.Converter;

import java.math.BigInteger;
import java.util.List;

@Service
public class NodeService implements  INodeService{

    private NodeRepository nodeRepository;


    public NodeService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Override
    public NodeDTO createNode(NodeDTO nodeDTO){
        return Converter.NodeEntityToDTO(nodeRepository.save(Converter.NodeDTOToEntity(nodeDTO)));
    }

    @Override
    public NodeDTO getNode(BigInteger nodeId) {
        return Converter.NodeEntityToDTO(nodeRepository.findById(nodeId).orElseThrow(() -> new NodeNotFoundException(nodeId)));
    }

    @Override
    public NodeDTO updateNode(BigInteger nodeId, NodeDTO nodeDTO){
        deleteNode(nodeId);
        nodeDTO.setId(nodeId.intValue());

        return createNode(nodeDTO);
    }

    @Override
    public void deleteNode(BigInteger nodeId){
        NodeEntity node = nodeRepository.findById(nodeId).orElseThrow(() -> new NodeNotFoundException(nodeId));

        nodeRepository.delete(node);
    }

    @Override
    public List<NodeDTO> getNodesByRadius(Double lon, Double lat, Double radius) {
        return Converter.MultipleNodeEntitiesToDTO(nodeRepository.getNodesByRadius(lon, lat, radius));
    }

}
