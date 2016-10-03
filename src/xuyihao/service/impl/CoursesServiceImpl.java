package xuyihao.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xuyihao.common.AppPropertiesLoader;
import xuyihao.entity.CommentCrs;
import xuyihao.entity.Courses;
import xuyihao.entity.LikeCrs;
import xuyihao.filerelate.entity.CoursesVedio;
import xuyihao.filerelate.entity.PhotoPath;
import xuyihao.filerelate.entity.VedioPath;
import xuyihao.filerelate.logic.CoursesVedioLogic;
import xuyihao.filerelate.logic.PhotoPathLogic;
import xuyihao.filerelate.logic.VedioPathLogic;
import xuyihao.logic.CommentCrsLogic;
import xuyihao.logic.CoursesLogic;
import xuyihao.logic.LikeCrsLogic;
import xuyihao.schedual.SchedualedPublishCachePool;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.FileUtils;
import xuyihao.tools.utils.ImageUtils;
import xuyihao.tools.utils.VedioUtils;

/**
 * 
 * @Author Xuyh created at 2016年8月26日 下午1:18:45
 */
@Component("CoursesService")
public class CoursesServiceImpl implements xuyihao.service.CoursesService {
	private String BASE_FILE_PATH = AppPropertiesLoader.getAppProperties().getProperty("BaseFilePath");
	private String RELATIVE_PATH = File.separator + "vedios" + File.separator + "courses" + File.separator;
	private String ABSOLUTE_PATH = BASE_FILE_PATH + RELATIVE_PATH;
	private String PHOTO_RELATIVE_PATH = File.separator + "photos" + File.separator + "courses" + File.separator;
	private String PHOTO_ABSOLUTE_PATH = BASE_FILE_PATH + PHOTO_RELATIVE_PATH;
	private String FFMPEG_TOOL_NAME = AppPropertiesLoader.getAppProperties()
			.getProperty("ffmpeg_executable_file_pathName");

	@Autowired
	private CoursesLogic coursesLogic;

	@Autowired
	private CoursesVedioLogic coursesVedioLogic;

	@Autowired
	private VedioPathLogic vedioPathLogic;

	@Autowired
	private PhotoPathLogic photoPathLogic;

	@Autowired
	private CommentCrsLogic commentCrsLogic;

	@Autowired
	private LikeCrsLogic likeCrsLogic;

	private HttpSession session = null;

	public void setPhotoPathLogic(PhotoPathLogic photoPathLogic) {
		this.photoPathLogic = photoPathLogic;
	}

	public void setVedioPathLogic(VedioPathLogic vedioPathLogic) {
		this.vedioPathLogic = vedioPathLogic;
	}

	public void setCoursesVedioLogic(CoursesVedioLogic coursesVedioLogic) {
		this.coursesVedioLogic = coursesVedioLogic;
	}

	public void setCoursesLogic(CoursesLogic coursesLogic) {
		this.coursesLogic = coursesLogic;
	}

	public void setCommentCrsLogic(CommentCrsLogic commentCrsLogic) {
		this.commentCrsLogic = commentCrsLogic;
	}

	public void setLikeCrsLogic(LikeCrsLogic likeCrsLogic) {
		this.likeCrsLogic = likeCrsLogic;
	}

	public void setSessionInfo(HttpSession session) {
		this.session = session;
	}

	public String addCourse(Courses course, HttpServletRequest request) {
		String Crs_ID = this.coursesLogic.saveCourse(course);
		String Vedio_ID = this.saveCoursesVedio(Crs_ID, request);
		String FirstPhoto_ID = this.vedioPathLogic.getVedioPathInfo(Vedio_ID).getFirstPhoto_ID();
		JSONObject json = new JSONObject();
		json.put("Crs_ID", Crs_ID);
		json.put("Vedio_ID", Vedio_ID);
		json.put("FirstPhoto_ID", FirstPhoto_ID);
		return json.toString();
	}

	public String deleteCourse(String courseId) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		Courses queryCourse = this.coursesLogic.getCoursesInfo(courseId);
		if (Acc_ID.equals(queryCourse.getAcc_ID())) {// 是本人的视频教程
			boolean flag = this.coursesLogic.deleteCourse(courseId);
			boolean flag2 = true;
			if (Acc_ID.equals(queryCourse.getAuthor_ID())) {// 是发布人，即视频源发布人,则连同视频文件一起删除
				flag2 = this.deleteCoursesVedio(courseId);
			}
			if ((flag && flag2) == true) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String changeCourseInformation(Courses course, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		if (Acc_ID != null && !Acc_ID.equals("")) {
			boolean flag = this.coursesLogic.changeCourseInfo(course);
			String vedioId = this.changeCoursesVedio(course.getCrs_ID(), request);
			String FirstPhoto_ID = this.vedioPathLogic.getVedioPathInfo(vedioId).getFirstPhoto_ID();
			json.put("result", flag);
			json.put("Vedio_ID", vedioId);
			json.put("FirstPhoto_ID", FirstPhoto_ID);
		} else {
			json.put("result", false);
			json.put("Vedio_ID", "");
			json.put("FirstPhoto_ID", "");
		}
		return json.toString();
	}

	public String getCourseInformation(String courseId) {
		JSONObject json = new JSONObject();
		Courses course = this.coursesLogic.getCoursesInfo(courseId);
		json.put(Courses.BASE_COURSES_PHYSICAL_ID, course.get_id());
		json.put(Courses.BASE_COURSES_ID, course.getCrs_ID());
		json.put(Courses.BASE_COURSES_NAME, course.getCrs_name());
		json.put(Courses.BASE_COURSES_ROUTE, course.getCrs_route());
		json.put(Courses.BASE_COURSES_ACCOUNT_ID, course.getAcc_ID());
		json.put(Courses.BASE_COURSES_AUTHOR_ACCOUNT_ID, course.getAuthor_ID());
		json.put(Courses.BASE_COURSES_REPOST_COUNT, course.getCrs_rep());
		json.put(Courses.BASE_COURSES_COMMON_COUNT, course.getCrs_comm());
		json.put(Courses.BASE_COURSES_LIKE_COUNT, course.getCrs_like());
		json.put(Courses.BASE_COURSES_ADD_TIME, course.getCrs_addTime());
		String vedioId = this.coursesVedioLogic.getCoursesVedioInfo(courseId).getVedio_ID();
		String firstPhotoId = this.vedioPathLogic.getVedioPathInfo(vedioId).getFirstPhoto_ID();
		json.put("Vedio_ID", vedioId);
		json.put("FirstPhoto_ID", firstPhotoId);
		// XXX 所有人都可以查看，因此无需检查发布者
		return json.toString();
	}

	public String shareCourse(String accountId, String courseId) {
		JSONObject json = new JSONObject();
		String Crs_ID = this.coursesLogic.shareCourse(accountId, courseId);
		// XXX 分享视频，视频共享一份
		if (Crs_ID == null || Crs_ID.equals("")) {
			json.put("result", false);
			json.put("Crs_ID", "");
		} else {
			boolean flag = this.shareCoursesVedio(courseId, Crs_ID);
			if (flag) {
				json.put("result", true);
				json.put("Crs_ID", Crs_ID);
			} else {
				json.put("result", false);
				json.put("Crs_ID", Crs_ID);
			}
		}
		return json.toString();
	}

	public String addCommentCourse(CommentCrs commentCrs) {
		JSONObject json = new JSONObject();
		String Comm_ID = this.commentCrsLogic.saveCommentCrs(commentCrs);
		if (Comm_ID == null || Comm_ID.equals("")) {
			json.put("result", false);
			json.put("Comm_ID", "");
		} else {
			json.put("result", true);
			json.put("Comm_ID", Comm_ID);
		}
		return json.toString();
	}

	public String deleteCommentCourse(String commentId) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		CommentCrs commentCrs = this.commentCrsLogic.getCommentCrsInfo(commentId);
		if (Acc_ID.equals(commentCrs.getAcc_ID())) {// 是本人的视频
			boolean flag = this.commentCrsLogic.deleteCommentCrs(commentId);
			if (flag) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String getCommentCourseInformation(String commentId) {
		// XXX 所有人可查看
		CommentCrs commentCrs = this.commentCrsLogic.getCommentCrsInfo(commentId);
		return commentCrs.toJSONString();
	}

	public String addLikeCourse(LikeCrs likeCrs) {
		JSONObject json = new JSONObject();
		String Like_ID = this.likeCrsLogic.saveLikeCrs(likeCrs);
		if (Like_ID == null || Like_ID.equals("")) {
			json.put("result", false);
			json.put("Like_ID", "");
		} else {
			json.put("result", true);
			json.put("Like_ID", Like_ID);
		}
		return json.toString();
	}

	public String getLikeCourseInformation(String likeId) {
		// XXX 所有人可查看
		LikeCrs likeCrs = this.likeCrsLogic.getLikeCrsInfo(likeId);
		return likeCrs.toJSONString();
	}

	private String saveCoursesVedio(String Crs_ID, HttpServletRequest request) {
		return changeCoursesVedio(Crs_ID, request);
	}

	private boolean shareCoursesVedio(String oldCrs_ID, String newCrs_ID) {
		CoursesVedio oldCoursesVedio = this.coursesVedioLogic.getCoursesVedioInfo(oldCrs_ID);
		boolean flag = this.coursesVedioLogic.saveCoursesVedio(newCrs_ID, oldCoursesVedio.getVedio_ID());
		if (flag) {
			return true;
		} else {
			return false;
		}
	}

	private boolean deleteCoursesVedio(String Crs_ID) {
		String absolutePath = ABSOLUTE_PATH;
		String photoAbsolutePath = PHOTO_ABSOLUTE_PATH;
		FileUtils.checkAndCreateFilePath(absolutePath);
		FileUtils.checkAndCreateFilePath(photoAbsolutePath);
		// 查找并删除相关文件
		String Vedio_ID = this.coursesVedioLogic.getCoursesVedioInfo(Crs_ID).getVedio_ID();
		VedioPath vedio = this.vedioPathLogic.getVedioPathInfo(Vedio_ID);
		String vedioName = vedio.getVedio_pathName();
		PhotoPath firstPhoto = this.photoPathLogic.getPhotoPathInfo(vedio.getFirstPhoto_ID());
		String firstPhotoName = firstPhoto.getPhoto_pathName();
		String firstPhotoThumbnailName = firstPhoto.getThumbnail_pathName();
		File file = null;
		file = new File(absolutePath + vedioName);
		if (file.exists()) {
			file.delete();
		}
		file = new File(photoAbsolutePath + firstPhotoName);
		if (file.exists()) {
			file.delete();
		}
		file = new File(photoAbsolutePath + firstPhotoThumbnailName);
		if (file.exists()) {
			file.delete();
		}
		// 删除数据库文件信息
		boolean flag1 = this.photoPathLogic.deletePhotoPath(vedio.getFirstPhoto_ID());
		boolean flag2 = this.vedioPathLogic.deleteVedioPath(Vedio_ID);
		boolean flag3 = this.coursesVedioLogic.deleteCoursesVedio(Crs_ID);
		return (flag1 && flag2 && flag3);
	}

	private String changeCoursesVedio(String Crs_ID, HttpServletRequest request) {
		if (Crs_ID == null || Crs_ID.equals("")) {
			return "";
		}
		String absolutePath = ABSOLUTE_PATH;
		String photoAbsolutePath = PHOTO_ABSOLUTE_PATH;
		FileUtils.checkAndCreateFilePath(absolutePath);
		FileUtils.checkAndCreateFilePath(photoAbsolutePath);
		try {
			// 检查数据库视频数据是否已经存在
			CoursesVedio coursesVedio = this.coursesVedioLogic.getCoursesVedioInfo(Crs_ID);
			if (coursesVedio.get_id() != 0) {
				// 如果存在则删除原来的视频和图片
				VedioPath vedio = this.vedioPathLogic.getVedioPathInfo(coursesVedio.getVedio_ID());
				String VedioPath = vedio.getVedio_pathName();
				File file = new File(absolutePath + VedioPath);
				if (file.exists()) {
					file.delete();
				}
				PhotoPath photo = this.photoPathLogic.getPhotoPathInfo(vedio.getFirstPhoto_ID());
				File filePhoto = new File(photoAbsolutePath + photo.getPhoto_pathName());
				if (filePhoto.exists()) {
					filePhoto.delete();
				}
				File fileThumbnail = new File(photoAbsolutePath + photo.getThumbnail_pathName());
				if (fileThumbnail.exists()) {
					fileThumbnail.delete();
				}
				this.coursesVedioLogic.deleteCoursesVedio(Crs_ID);
			}
			Part part = request.getPart("file");
			if (part == null) {
				return "";
			} else {
				String fileTypeName = "." + FileUtils.getFileType(part);
				String vedioFileName = "Vedio" + Crs_ID + DateUtils.currentDate() + fileTypeName;
				String firstPhotoName = "VedioFirstPhoto" + Crs_ID + DateUtils.currentDate() + ".jpg";// 视频首帧图
				String firstPhotoThumbnailName = "VedioThumbnailPhoto" + Crs_ID + DateUtils.currentDate() + ".jpg";// 视频首帧缩略图
				// 保存视频
				// TODO 判断视频是否是mp4,flv等格式，如果不是则需要转换(视频工具还有一些问题，该功能暂时忽略)
				FileUtils.writePartToDisk(part, absolutePath + vedioFileName);
				// 生成视频首帧的图片以及缩略图
				// XXX 这个方法开启进程调用但是不会阻塞,会影响之后的方法调用(缩略图生成方法找不到文件)
				VedioUtils.cutoutVedio(FFMPEG_TOOL_NAME, absolutePath + vedioFileName, photoAbsolutePath + firstPhotoName,
						3.3f);
				// XXX 判断首帧图片是否生成,并等待两秒使图片完成生成
				while (true) {
					File photoRaw = new File(photoAbsolutePath + firstPhotoName);
					if (photoRaw.exists()) {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						break;
					}
				}
				ImageUtils.zoomImageScale(photoAbsolutePath + firstPhotoName, photoAbsolutePath + firstPhotoThumbnailName, 448);
				// 数据库数据
				String firstPhotoId = "";
				firstPhotoId = this.photoPathLogic.savePhotoPath(firstPhotoName, firstPhotoThumbnailName);
				String Vedio_ID = this.vedioPathLogic.saveVedioPath(vedioFileName, firstPhotoId);
				boolean result = this.coursesVedioLogic.saveCoursesVedio(Crs_ID, Vedio_ID);
				if (result) {
					return Vedio_ID;
				} else {
					return "";
				}
			}
		} catch (ServletException e1) {
			e1.printStackTrace();
			return "";
		} catch (IOException e2) {
			e2.printStackTrace();
			return "";
		}
	}

	public String getCoursesVedioAndPhotoIds(String Crs_ID) {
		JSONObject json = new JSONObject();
		String vedioId = this.coursesVedioLogic.getCoursesVedioInfo(Crs_ID).getVedio_ID();
		String firstPhotoId = this.vedioPathLogic.getVedioPathInfo(vedioId).getFirstPhoto_ID();
		json.put("Vedio_ID", vedioId);
		json.put("FirstPhoto_ID", firstPhotoId);
		return json.toString();
	}

	public String getCoursesVedioId(String Crs_ID) {
		JSONObject json = new JSONObject();
		CoursesVedio coursesVedio = this.coursesVedioLogic.getCoursesVedioInfo(Crs_ID);
		json.put("Vedio_ID", coursesVedio.getVedio_ID());
		return json.toString();
	}

	public String getFirstPhotoIdByVedioId(String Vedio_ID) {
		JSONObject json = new JSONObject();
		VedioPath vedio = this.vedioPathLogic.getVedioPathInfo(Vedio_ID);
		json.put("FirstPhoto_ID", vedio.getFirstPhoto_ID());
		return json.toString();
	}

	public String getVedioByVedioId(String Vedio_ID) {
		String absolutePath = ABSOLUTE_PATH;
		FileUtils.checkAndCreateFilePath(absolutePath);
		VedioPath vedio = this.vedioPathLogic.getVedioPathInfo(Vedio_ID);
		String vedioName = vedio.getVedio_pathName();
		String vedioFilePathName = absolutePath + vedioName;
		return vedioFilePathName;
	}

	public String getPhotoByPhotoId(String Photo_ID) {
		String photoAbsolutePath = PHOTO_ABSOLUTE_PATH;
		FileUtils.checkAndCreateFilePath(photoAbsolutePath);
		String pathName = this.photoPathLogic.getPhotoPathInfo(Photo_ID).getPhoto_pathName();
		String realPath = photoAbsolutePath + pathName;
		return realPath;
	}

	public String getThumbnailPhotoByPhotoId(String Photo_ID) {
		String photoAbsolutePath = PHOTO_ABSOLUTE_PATH;
		FileUtils.checkAndCreateFilePath(photoAbsolutePath);
		String pathName = this.photoPathLogic.getPhotoPathInfo(Photo_ID).getThumbnail_pathName();
		String realPath = photoAbsolutePath + pathName;
		return realPath;
	}

	public String getCachedPublishingCourses() {
		List<Courses> coursesList = SchedualedPublishCachePool.getLatestCoursesPool();
		JSONArray array = this.copyCoursesList(coursesList);
		JSONObject json = new JSONObject();
		json.put("coursesList", array);
		return json.toString();
	}

	public String getLatestCourses(int page, int size) {
		JSONArray array = this.copyCoursesList(this.coursesLogic.getLatestCourses(page, size));
		JSONObject json = new JSONObject();
		json.put("coursesList", array);
		return json.toString();
	}

	/**
	 * <pre>
	 * 转换响应数据
	 * 添加Vedio_ID, FirstPhoto_ID,完善响应数据
	 * </pre>
	 * 
	 * @return
	 */
	private JSONArray copyCoursesList(List<Courses> coursesList) {
		// XXX 形成响应消息
		JSONArray array = new JSONArray();
		for (Courses course : coursesList) {
			JSONObject json = new JSONObject();
			json.put(Courses.BASE_COURSES_PHYSICAL_ID, course.get_id());
			json.put(Courses.BASE_COURSES_ID, course.getCrs_ID());
			json.put(Courses.BASE_COURSES_NAME, course.getCrs_name());
			json.put(Courses.BASE_COURSES_ROUTE, course.getCrs_route());
			json.put(Courses.BASE_COURSES_ACCOUNT_ID, course.getAcc_ID());
			json.put(Courses.BASE_COURSES_AUTHOR_ACCOUNT_ID, course.getAuthor_ID());
			json.put(Courses.BASE_COURSES_REPOST_COUNT, course.getCrs_rep());
			json.put(Courses.BASE_COURSES_COMMON_COUNT, course.getCrs_comm());
			json.put(Courses.BASE_COURSES_LIKE_COUNT, course.getCrs_like());
			json.put(Courses.BASE_COURSES_ADD_TIME, course.getCrs_addTime());
			String vedioId = this.coursesVedioLogic.getCoursesVedioInfo(course.getCrs_ID()).getVedio_ID();
			String firstPhotoId = this.vedioPathLogic.getVedioPathInfo(vedioId).getFirstPhoto_ID();
			json.put("Vedio_ID", vedioId);
			json.put("FirstPhoto_ID", firstPhotoId);
			array.add(json);
		}
		return array;
	}
}