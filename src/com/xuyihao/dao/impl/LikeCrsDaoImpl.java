package com.xuyihao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xuyihao.dao.LikeCrsDao;
import com.xuyihao.entity.LikeCrs;
import com.xuyihao.tools.ConnDB;

/*
 * create by xuyihao on 2016/4/28
 * @describe 打赏视频相关数据库操作封装类
 * @attention 打赏视频的数据库操作，只有添加信息入表，没有删除操作，因为打赏是不可以撤销的
 * */
public class LikeCrsDaoImpl implements LikeCrsDao {
	private ConnDB conn = new ConnDB();

	public LikeCrsDaoImpl() {
	}

	public void setConn(ConnDB conn) {
		this.conn = conn;
	}

	@Override
	public boolean saveLikeCrs(LikeCrs likeCrs) {
		String sql = "insert into " + LikeCrs.BASE_TABLE_NAME + " values(null, '" + likeCrs.getLike_ID() + "', '"
				+ likeCrs.getAcc_ID() + "', '" + likeCrs.getRep_ID() + "', '" + likeCrs.getCrs_ID() + "', "
				+ likeCrs.getLike_ryb() + ", '" + likeCrs.getLike_addTime() + "')";
		boolean flag = false;
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public boolean deleteLikeCrs(String Like_ID) {
		boolean flag = false;
		String sql = "delete from " + LikeCrs.BASE_TABLE_NAME + " where " + LikeCrs.BASE_LIKE_COURSE_ID + " = '" + Like_ID
				+ "'";
		if (this.conn.executeUpdate(sql) == 0) {
			flag = false;
		} else {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public boolean updateLikeCrs(LikeCrs likeCrs) {
		boolean flag = false;
		String sql = "update " + LikeCrs.BASE_TABLE_NAME + " set " + LikeCrs.BASE_LIKE_COURSE_ACCOUNT_ID + " = '"
				+ likeCrs.getAcc_ID() + "', " + LikeCrs.BASE_LIKE_COURSE_PUBLISHER_REP_ID + " = '" + likeCrs.getRep_ID() + "', "
				+ LikeCrs.BASE_LIKE_COURSE_COURSE_ID + " = '" + likeCrs.getCrs_ID() + "', " + LikeCrs.BASE_LIKE_COURSE_RYB_COUNT
				+ " = " + likeCrs.getLike_ryb() + ", " + LikeCrs.BASE_LIKE_COURSE_ADD_TIME + " = '" + likeCrs.getLike_addTime()
				+ "' where " + LikeCrs.BASE_LIKE_COURSE_ID + " = '" + likeCrs.getLike_ID() + "'";
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public boolean updateLikeCrs(String update) {
		boolean flag = false;
		if (this.conn.executeUpdate(update) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public LikeCrs queryById(String Like_ID) {
		String sql = "select * from " + LikeCrs.BASE_TABLE_NAME + " where " + LikeCrs.BASE_LIKE_COURSE_ID + " = '" + Like_ID
				+ "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		LikeCrs likeCrs = getLikeCrsFromResultSet(resultSet);
		this.conn.close();
		return likeCrs;
	}

	@Override
	public List<LikeCrs> queryByAccountId(String Acc_ID) {
		String sql = "select * from " + LikeCrs.BASE_TABLE_NAME + " where " + LikeCrs.BASE_LIKE_COURSE_ACCOUNT_ID + " = '"
				+ Acc_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<LikeCrs> likeCrsList = getLikeCrsListFromResultSet(resultSet);
		this.conn.close();
		return likeCrsList;
	}

	@Override
	public List<LikeCrs> queryByReporterId(String Rep_ID) {
		String sql = "select * from " + LikeCrs.BASE_TABLE_NAME + " where " + LikeCrs.BASE_LIKE_COURSE_PUBLISHER_REP_ID
				+ " = '" + Rep_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<LikeCrs> likeCrsList = getLikeCrsListFromResultSet(resultSet);
		this.conn.close();
		return likeCrsList;
	}

	@Override
	public List<LikeCrs> queryByCourseId(String Crs_ID) {
		String sql = "select * from " + LikeCrs.BASE_TABLE_NAME + " where " + LikeCrs.BASE_LIKE_COURSE_COURSE_ID + " = '"
				+ Crs_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<LikeCrs> likeCrsList = getLikeCrsListFromResultSet(resultSet);
		this.conn.close();
		return likeCrsList;
	}

	@Override
	public ResultSet queryBySql(String sql) {
		ResultSet resultSet = this.conn.executeQuery(sql);
		return resultSet;
	}

	/**
	 * 通过ResultSet获取对象
	 *
	 *
	 */
	private LikeCrs getLikeCrsFromResultSet(ResultSet resultSet) {
		LikeCrs likeCrs = new LikeCrs();
		try {
			if (resultSet.next()) {
				likeCrs.set_id(resultSet.getLong(LikeCrs.BASE_LIKE_COURSE_PHYSICAL_ID));
				likeCrs.setLike_ID(resultSet.getString(LikeCrs.BASE_LIKE_COURSE_ID));
				likeCrs.setAcc_ID(resultSet.getString(LikeCrs.BASE_LIKE_COURSE_ACCOUNT_ID));
				likeCrs.setRep_ID(resultSet.getString(LikeCrs.BASE_LIKE_COURSE_PUBLISHER_REP_ID));
				likeCrs.setCrs_ID(resultSet.getString(LikeCrs.BASE_LIKE_COURSE_COURSE_ID));
				likeCrs.setLike_ryb(resultSet.getInt(LikeCrs.BASE_LIKE_COURSE_RYB_COUNT));
				likeCrs.setLike_addTime(resultSet.getString(LikeCrs.BASE_LIKE_COURSE_ADD_TIME));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return likeCrs;
	}

	/**
	 * 通过ResultSet获取对象
	 *
	 *
	 */
	private List<LikeCrs> getLikeCrsListFromResultSet(ResultSet resultSet) {
		List<LikeCrs> likeCrsList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				LikeCrs likeCrs = new LikeCrs();
				likeCrs.set_id(resultSet.getLong(LikeCrs.BASE_LIKE_COURSE_PHYSICAL_ID));
				likeCrs.setLike_ID(resultSet.getString(LikeCrs.BASE_LIKE_COURSE_ID));
				likeCrs.setAcc_ID(resultSet.getString(LikeCrs.BASE_LIKE_COURSE_ACCOUNT_ID));
				likeCrs.setRep_ID(resultSet.getString(LikeCrs.BASE_LIKE_COURSE_PUBLISHER_REP_ID));
				likeCrs.setCrs_ID(resultSet.getString(LikeCrs.BASE_LIKE_COURSE_COURSE_ID));
				likeCrs.setLike_ryb(resultSet.getInt(LikeCrs.BASE_LIKE_COURSE_RYB_COUNT));
				likeCrs.setLike_addTime(resultSet.getString(LikeCrs.BASE_LIKE_COURSE_ADD_TIME));
				likeCrsList.add(likeCrs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return likeCrsList;
	}

	@Override
	public void closeDBConnection() {
		this.conn.close();
	}
}
