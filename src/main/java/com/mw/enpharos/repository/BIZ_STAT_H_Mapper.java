package com.mw.enpharos.repository;

import org.qlrm.mapper.JpaResultMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mw.enpharos.dto.TpsResDTO;
import com.mw.enpharos.entity.BIZ_STAT_H;

import java.sql.Timestamp;
import java.util.List;

/*
- JPA 처리를 담당하는 Repository는 기본적으로 4가지가 있다. (T : Entity의 타입클래스, ID : P.K 값의 Type)
1) Repository<T, ID>
2) CrudRepository<T, ID>
3) PagingAndSortingRepository<T, ID>
4) JpaRepository<T, ID>
출처: https://goddaehee.tistory.com/209 [갓대희의 작은공간]
*/
	
@Repository
public interface BIZ_STAT_H_Mapper extends JpaRepository<BIZ_STAT_H, Long>{
	
	List<BIZ_STAT_H> findAll();

	BIZ_STAT_H findByTimeslice(@Param("timeslice") Timestamp timeslice);
	
	BIZ_STAT_H findByTxcode(@Param("tx_code") String txcode);
	
	
	/*
    @Update("UPDATE WSC.BOARDS SET TITLE = #{title}, CONTENT = #{content} WHERE ID = #{id}")
    void update(Board board);
    
    @Select("SELECT * FROM WSC.BOARDS")
    List<Board> findAll();
    
    @Select("SELECT * FROM WSC.BOARDS WHERE ID = #{id}")
    Board findOne(@Param("id") int id);

    @Delete("DELETE FROM WSC.BOARDS WHERE ID = #{id}")
    void delete(@Param("id") int id);

    @Select("SELECT * FROM WSC.BOARDS WHERE USER_ID = #{userId}")
    Board findOneByUserId(@Param("userId") int userId);
    
    @Insert("INSERT INTO WSC.BOARDS (USER_ID, TITLE, CONTENT, CREATE_TIME) VALUES (#{userId}, #{title}, #{content}, #{create_time})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)

	BIZ_STAT_H save(BIZ_STAT_H biz_stat_h);
	BIZ_STAT_H deleteById(@Param("id") int id);
    */
}
