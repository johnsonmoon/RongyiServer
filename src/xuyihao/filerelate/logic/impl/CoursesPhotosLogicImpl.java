package xuyihao.filerelate.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xuyihao.filerelate.dao.CoursesPhotosDao;
import xuyihao.filerelate.entity.CoursesPhotos;
import xuyihao.filerelate.logic.CoursesPhotosLogic;
import xuyihao.tools.utils.DateUtils;

@Component("CoursesPhotosLogic")
public class CoursesPhotosLogicImpl implements CoursesPhotosLogic {
	@Autowired
	private CoursesPhotosDao coursesPhotosDao;

	public void setCoursesPhotosDao(CoursesPhotosDao coursesPhotosDao) {
		this.coursesPhotosDao = coursesPhotosDao;
	}

	public boolean saveCoursesPhotos(String Crs_ID, String HeadPhoto_ID, List<String> CrsPhotoIdList) {
		CoursesPhotos coursesPhotos = new CoursesPhotos();
		coursesPhotos.setCrs_ID(Crs_ID);
		coursesPhotos.setHeadPhoto_ID(HeadPhoto_ID);
		if (CrsPhotoIdList != null && CrsPhotoIdList.size() > 0) {
			String idCombine = "";
			for (String id : CrsPhotoIdList) {
				idCombine += (id + "&&");
			}
			idCombine = idCombine.substring(0, idCombine.length() - 2);
			coursesPhotos.setPhoto_ID_Combine(idCombine);
		}
		coursesPhotos.setCrsPhoto_addTime(DateUtils.currentDateTime());
		return this.coursesPhotosDao.saveCoursesPhotos(coursesPhotos);
	}

	public boolean deleteCoursesPhotos(String Crs_ID) {
		return this.coursesPhotosDao.deleteCoursesPhotos(Crs_ID);
	}

	public boolean changeCoursesPhotosInfo(String Crs_ID, String HeadPhoto_ID, List<String> CrsPhotoIdList) {
		CoursesPhotos coursesPhotos = this.coursesPhotosDao.query(Crs_ID);
		if (coursesPhotos.get_id() == 0) {
			return false;
		}
		if (HeadPhoto_ID != null && !HeadPhoto_ID.equals("")) {
			coursesPhotos.setHeadPhoto_ID(HeadPhoto_ID);
		}
		if (CrsPhotoIdList != null && CrsPhotoIdList.size() > 0) {
			String idCombine = "";
			for (String id : CrsPhotoIdList) {
				idCombine += (id + "&&");
			}
			idCombine = idCombine.substring(0, idCombine.length() - 2);
			coursesPhotos.setPhoto_ID_Combine(idCombine);
		}
		return this.coursesPhotosDao.updateCoursesPhotos(coursesPhotos);
	}

	public CoursesPhotos getCoursesPhototsInfo(String Crs_ID) {
		return this.coursesPhotosDao.query(Crs_ID);
	}

	public String getCoursesHeadPhoto(String Crs_ID) {
		CoursesPhotos coursesPhotos = this.coursesPhotosDao.query(Crs_ID);
		return coursesPhotos.getHeadPhoto_ID();
	}

	public List<String> getCoursesOtherPhotos(String Crs_ID) {
		CoursesPhotos coursesPhotos = this.coursesPhotosDao.query(Crs_ID);
		String[] idArray = coursesPhotos.getPhoto_ID_Combine().split("&&");
		List<String> idList = new ArrayList<String>();
		for (int i = 0; i < idArray.length; i++) {
			idList.add(idArray[i]);
		}
		return idList;
	}
}