package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainAssociationEntity; 
 import java.util.List; 
 import org.springframework.stereotype.Repository; 
  
 /** 
  * Repository interface for fetching association data between steps and services. 
  */ 
 @Repository 
 public interface InfrastructureAssociationRepositoryPort { 
     /** 
      * Fetches all association records from the database. 
      *  
      * @return a list of DomainAssociationEntity objects representing all associations. 
      * @throws DataAccessException if there is an error accessing the data. 
      */ 
     List<DomainAssociationEntity> findAll() throws DataAccessException; 
  
     /** 
      * Fetches association records that match a given service. 
      *  
      * @param service the service to match. 
      * @return a list of DomainAssociationEntity objects representing the matching associations. 
      * @throws DataAccessException if there is an error accessing the data. 
      */ 
     List<DomainAssociationEntity> findByService(String service) throws DataAccessException; 
 }