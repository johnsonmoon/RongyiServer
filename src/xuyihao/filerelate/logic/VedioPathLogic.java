package xuyihao.filerelate.logic;

import xuyihao.filerelate.entity.VedioPath;

/**
 * 
 * @author Xuyh at 2016年9月3日 上午11:53:52.
 *
 */
public interface VedioPathLogic {
	/**
	 * 存储视频路径
	 * 
	 * @param Vedio_pathName
	 * @param Thumbnail_pathName
	 * @return
	 */
	public String saveVedioPath(String Vedio_pathName, String Thumbnail_pathName);

	/**
	 * 删除视频路径
	 * 
	 * @param Vedio_ID
	 * @return
	 */
	public boolean deleteVedioPath(String Vedio_ID);

	/**
	 * 修改视频路径
	 * 
	 * @param Vedio_pathName
	 * @param Thumbnail_pathName
	 * @return
	 */
	public boolean changeVedioPathInfo(String Vedio_ID, String Vedio_pathName, String Thumbnail_pathName);

	/**
	 * 获取视频路径信息
	 * 
	 * @param Vedio_ID
	 * @return
	 */
	public VedioPath getVedioPathInfo(String Vedio_ID);
}