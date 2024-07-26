package ai.shreds.Infrastructure;

import ai.shreds.Domain.DomainAssociationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class InfrastructureAssociationRepositoryImpl implements InfrastructureAssociationRepositoryPort {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public InfrastructureAssociationRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<DomainAssociationEntity> findAll() {
        return mongoTemplate.findAll(DomainAssociationEntity.class);
    }

    @Override
    public List<DomainAssociationEntity> findByService(String service) {
        Query query = new Query();
        query.addCriteria(Criteria.where("services").in(service));
        return mongoTemplate.find(query, DomainAssociationEntity.class);
    }
}