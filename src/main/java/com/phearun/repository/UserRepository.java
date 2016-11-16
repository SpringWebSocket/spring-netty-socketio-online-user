package com.phearun.repository;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.phearun.entity.Role;
import com.phearun.entity.User;

@Repository
public interface UserRepository {
	
	@Select(SQL.FIND_USER_BY_USERNAME)
	@Results({
		@Result(property="id", column="id"),
		@Result(property="roles", column="id", many = @Many(select="findRolesByUserId"))
	})
	User findUserByUsername(String username);

	@Select(SQL.FIND_ROLES_BY_USERID)
	List<Role> findRolesByUserId(@Param("id") int id);

	@Update(SQL.UPDATE_ONLINE_STATUS)
	boolean updateOnlineStatus(String clientId);
	
	
	interface SQL{
		String FIND_USER_BY_USERNAME = ""
				+ "	SELECT "
				+ "		id, "
				+ "		username, "
				+ "		email, "
				+ "		password, "
				+ "		status, "
				+ "		online "
				+ "	FROM "
				+ "		tbuser "
				+ "	WHERE "
				+ "		username = #{username}";
		
		String FIND_ROLES_BY_USERID = ""
				+ "	SELECT "
				+ "		r.id, "
				+ "		r.name "
				+ "	FROM "
				+ "		tbuser_detail ud "
				+ "	INNER JOIN "
				+ "		tbrole r ON ud.role_id = r. ID "
				+ "	WHERE "
				+ "		ud.user_id = #{id}";
		
		String UPDATE_ONLINE_STATUS = ""
				+ "	UPDATE "
				+ "		tbuser"
				+ "	SET "
				+ "		online = NOT COALESCE (online, 'f')"
				+ " WHERE "
				+ "		client_id = #{clientId}";
	}
	
}
