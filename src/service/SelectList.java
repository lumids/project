package service;

import java.util.List;

import dao.AniInfo;
import dao.AniInfoDao;
import dao.ItemInfo;
import dao.ItemInfoDao;

public class SelectList {
	public List<AniInfo> selectAll() {
		AniInfoDao pd = AniInfoDao.getInstance();
		List<AniInfo> list = pd.selectAll("all");
		
		return list;
	}
	public List<ItemInfo> selectAll2() {
		ItemInfoDao pd = ItemInfoDao.getInstance();
		List<ItemInfo> list = pd.selectAll();
		
		return list;
	}
}
