package com.casemodule6_be.repository.custom_repositories;

import com.casemodule6_be.model.BillDetail;
import com.casemodule6_be.model.Room;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class CustomRoomRepository {
    @PersistenceContext
    private EntityManager em;

//    public List<Object[]> topRent() {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
//        Root<Room> rootRoom = query.from(Room.class);
//        Root<Image> rootImage = query.from(Image.class);
//        Root<BillDetail> rootBillDetail = query.from(BillDetail.class);
//
//        query.where(cb.equal(rootRoom.get("id"), rootImage.get("room")));
//        query.where(cb.equal(rootRoom.get("id"), rootBillDetail.get("room")));
//
//        query = query.multiselect(rootBillDetail.get("room").as(BigDecimal.class),
//                cb.function("GROUP_CONCAT", String.class, rootImage.get("name")),
//                rootRoom.get("price").as(BigDecimal.class),
//                cb.sum(rootBillDetail.get("amountDay").as(BigDecimal.class)));
//
//
//        query = query.groupBy(rootBillDetail.get("room").as(String.class));
//        query = query.orderBy(cb.desc(cb.sum(rootBillDetail.get("amountDay").as(BigDecimal.class))));
//
//
//        Query q = em.createQuery(query);
//        List<Object[]> kq = q.getResultList();
//        return kq;
//    }
}
