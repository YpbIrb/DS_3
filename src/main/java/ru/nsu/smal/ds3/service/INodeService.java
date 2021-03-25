package ru.nsu.smal.ds3.service;

import ru.nsu.smal.ds3.dao.NodeDTO;

import java.math.BigInteger;
import java.util.List;

public interface INodeService {

    NodeDTO createNode(NodeDTO nodeDTO);

    NodeDTO getNode(BigInteger nodeId) throws Exception;

    NodeDTO updateNode(BigInteger nodeId, NodeDTO nodeDTO) throws Exception;

    void deleteNode(BigInteger nodeId) throws Exception;

    List<NodeDTO> getNodesByRadius(Double lon, Double lat, Double radius);
}
