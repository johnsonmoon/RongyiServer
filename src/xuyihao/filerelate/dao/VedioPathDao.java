package xuyihao.filerelate.dao;

import xuyihao.filerelate.entity.VedioPath;

/**
 * 
 * @author Xuyh at 2016年8月30日 上午9:48:55.
 *
 */
public interface VedioPathDao {
	/**
	 * 添加视频
	 * 
	 * @param vedioPath
	 * @return
	 */
	public boolean saveVedioPath(VedioPath vedioPath);

	/**
	 * 删除视频
	 * 
	 * @param Vedio_ID
	 * @return
	 */
	public boolean deleteVedioPath(String Vedio_ID);

	/**
	 * 修改视频信息
	 * 
	 * @param vedioPath
	 * @return
	 */
	public boolean updateVedioPath(VedioPath vedioPath);

	/**
	 * 修改视频信息
	 * 
	 * @param update
	 * @return
	 */
	public boolean updateVedioPath(String update);

	/**
	 * 查找
	 * 
	 * @param Vedio_ID
	 * @return
	 */
	public VedioPath queryById(String Vedio_ID);

	/**
	 * 查找
	 * 
	 * @param query
	 * @return
	 */
	public VedioPath QueryBySql(String query);

	/**
	 * 关闭数据库连接(对于返回ResultSet的查询，其余的不用)
	 * 
	 */
	public void closeDBConnection();
}