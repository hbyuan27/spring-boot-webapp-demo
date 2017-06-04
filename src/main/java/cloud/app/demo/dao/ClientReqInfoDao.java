package cloud.app.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cloud.app.demo.entity.ClientReqInfo;

@Repository
public interface ClientReqInfoDao extends JpaRepository<ClientReqInfo, Long> {

}
