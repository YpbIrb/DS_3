package ru.nsu.smal.ds3.utility;

import ru.nsu.smal.ds3.dao.NodeDTO;
import ru.nsu.smal.ds3.dao.TagDTO;
import ru.nsu.smal.ds3.entity.NodeEntity;
import ru.nsu.smal.ds3.entity.TagEntity;
import ru.nsu.smal.ds3.osm.model.generated.Node;
import ru.nsu.smal.ds3.osm.model.generated.Tag;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public static NodeDTO NodeToDTO(Node node ){
        List<TagDTO> tags = node.getTag().stream()
                .map(tag -> TagToDTO(tag))
                .collect(Collectors.toList());

        return
                new NodeDTO(node.getId().intValue(), node.getLat(), node.getLon(), node.getUser(), node.getUid().intValue(),
                        node.getVersion().intValue(), node.getChangeset().intValue(), new Date(node.getTimestamp().toGregorianCalendar().getTime().getTime()), tags);
    }

    public static TagDTO TagToDTO(Tag tag ){
        return new TagDTO( tag.getK(), tag.getV());
    }


    public static NodeEntity NodeDTOToEntity(NodeDTO node){
        List<TagEntity> tags = node.getTags().stream()
                .map(tag -> TagDTOToEntity(tag))
                .collect(Collectors.toList());

        return new NodeEntity(BigInteger.valueOf(node.getId()), node.getLat(), node.getLon(), node.getUser_name(), BigInteger.valueOf(node.getUid()),
                BigInteger.valueOf(node.getVersion()), BigInteger.valueOf(node.getChangeset()), node.getTimestamp(), tags);
    }

    public static TagEntity TagDTOToEntity(TagDTO tag){
        return new TagEntity(tag.getK(), tag.getV());
    }


    public static NodeDTO NodeEntityToDTO(NodeEntity node ){
        List<TagDTO> tags = node.getTags().stream()
                .map(tag -> TagEntityToDTO(tag))
                .collect(Collectors.toList());

        return
                new NodeDTO(node.getId().intValue(), node.getLat(), node.getLon(), node.getUser_name(), node.getUid().intValue(),
                        node.getVersion().intValue(), node.getChangeset().intValue(), node.getTimestamp(), tags);
    }

    public static TagDTO TagEntityToDTO(TagEntity tag){
        return new TagDTO(tag.getK(), tag.getV());
    }


    public static List<NodeDTO> MultipleNodeEntitiesToDTO(List<NodeEntity> nodeEntities){

        return nodeEntities.stream().map(e -> NodeEntityToDTO(e)).collect(Collectors.toList());

    }

}
