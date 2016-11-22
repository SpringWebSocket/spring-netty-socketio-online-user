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
	
	@Update(SQL.TOGGLE_ONLINE_STATUS)
	boolean toggleOnlineStatus(int id);
	
	@Update(SQL.UPDATE_TO_ONLINE)
	boolean updateToOnline(int id);
	
	@Update(SQL.UPDATE_TO_OFFLINE)
	boolean updateToOffline(int id);
	
	
	@Update(SQL.UPDATE_CLIENT_ID)
	boolean updateClientIdByUserId(@Param("userId") int userId, @Param("clientId") String clientId);
	
	@Select(SQL.FIND_ALL_ONLINE_USER)
	List<User> findAllOnlineUsers();
	
	@Update(SQL.UPDATE_ONLINE_AND_CLIENT_ID)
	boolean updateOnlineAndClientIdByUserId(@Param("userId") int userId, @Param("clientId") String clientId);
	
	@Update(SQL.UPDATE_OFFLINE_BY_CLIENT_ID)
	boolean updateOfflineByClientId(String clientId);
	
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
		
		String UPDATE_CLIENT_ID = ""
				+ "	UPDATE tbuser"
				+ "	SET "
				+ "		client_id = #{clientId}"
				+ "	WHERE "
				+ "		id = #{userId}";
		
		String FIND_ALL_ONLINE_USER = ""
				+ "	SELECT "
				+ "		id, "
				+ "		username,"
				+ "		email,"
				+ "		client_id AS clientId"
				+ "	FROM"
				+ "		tbuser"
				+ "	WHERE"
				+ "		online = true";
		
		String TOGGLE_ONLINE_STATUS = ""
				+ "	UPDATE "
				+ "		tbuser"
				+ "	SET "
				+ "		online = NOT COALESCE (online, 'f')"
				+ " WHERE "
				+ "		id = #{id}";

		String UPDATE_TO_ONLINE = ""
				+ "	UPDATE"
				+ "		tbuser"
				+ "	SET "
				+ "		online = true"
				+ "	WHERE "
				+ "		id = #{id}";
		
		String UPDATE_TO_OFFLINE = ""
				+ "	UPDATE"
				+ "		tbuser"
				+ "	SET "
				+ "		online = false"
				+ "	WHERE "
				+ "		id = #{id}";
		
		String UPDATE_ONLINE_AND_CLIENT_ID = ""
				+ "	UPDATE "
				+ "		tbuser"
				+ "	SET"
				+ "		online = true, "
				+ "		client_id = #{clientId}"
				+ "	WHERE"
				+ "		id = #{userId}";
		
		String UPDATE_OFFLINE_BY_CLIENT_ID = ""
				+ "	UPDATE "
				+ "		tbuser"
				+ "	SET"
				+ "		online = false "
				+ "	WHERE"
				+ "		client_id = #{clientId}";
		
	}
	
}
