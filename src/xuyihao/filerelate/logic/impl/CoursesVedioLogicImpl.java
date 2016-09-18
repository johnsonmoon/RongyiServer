package xuyihao.filerelate.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xuyihao.filerelate.dao.CoursesVedioDao;
import xuyihao.filerelate.entity.CoursesVedio;
import xuyihao.filerelate.logic.CoursesVedioLogic;
import xuyihao.tools.utils.DateUtils;

/**
 * 
 * @Author Xuyh created at 2016年9月18日 下午5:22:09
 */
@Component("CoursesVedioLogic")
public class CoursesVedioLogicImpl implements CoursesVedioLogic {
	@Autowired
	private CoursesVedioDao coursesVedioDao;

	public void setCoursesVedioDao(CoursesVedioDao coursesVedioDao) {
		this.coursesVedioDao = coursesVedioDao;
	}

	public boolean saveCoursesVedio(String Crs_ID, String Vedio_ID) {
		CoursesVedio coursesVedio = new CoursesVedio();
		coursesVedio.setCrs_ID(Crs_ID);
		coursesVedio.setVedio_ID(Vedio_ID);
		coursesVedio.setCrsVedio_addTime(DateUtils.currentDateTime());
		return this.coursesVedioDao.saveCoursesVedio(coursesVedio);
	}

	public boolean deleteCoursesVedio(String Crs_ID) {
		return this.coursesVedioDao.deleteCoursesVedio(Crs_ID);
	}

	public boolean changeCoursesVedioInfo(String Crs_ID, String Vedio_ID) {
		CoursesVedio coursesVedio = new CoursesVedio();
		coursesVedio.setCrs_ID(Crs_ID);
		coursesVedio.setVedio_ID(Vedio_ID);
		return this.coursesVedioDao.updateCoursesVedio(coursesVedio);
	}

	public CoursesVedio getCoursesVedioInfo(String Crs_ID) {
		return this.coursesVedioDao.query(Crs_ID);
	}
}