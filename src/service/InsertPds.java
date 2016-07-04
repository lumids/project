package service;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.AniInfo;
import dao.AniInfoDao;
import dao.ItemInfo;
import dao.ItemInfoDao;
import dao.MapItemInfo;
import dao.MapItemInfoDao;
public class InsertPds {
	public int insert(HttpServletRequest request) {
		int result = 0; MultipartRequest mr = null;
		String save = "/upload";
		String real = request.getSession()
			.getServletContext().getRealPath(save);
		int maxSize = 10 * 1024 * 1024;
		try { mr = new MultipartRequest(request, real, maxSize,
				"utf-8",new DefaultFileRenamePolicy());
		} catch (IOException e) {
			System.out.println(e.getMessage()); }
		String aniPic1 = mr.getFilesystemName("aniFileName1");
		String aniPic2 = mr.getFilesystemName("aniFileName2");
		String aniName1 = mr.getParameter("aniName1");
		String aniName2 = mr.getParameter("aniName2");
		String aniHello1 =  mr.getParameter("aniHello1");
		String aniHello2 =  mr.getParameter("aniHello2");
		String aniHello3 =  mr.getParameter("aniHello3");
		int aniMaxStat1 = Integer.parseInt(mr.getParameter("aniMaxStat1"));
		int aniMaxStat2 = Integer.parseInt(mr.getParameter("aniMaxStat2"));
		int aniPrice = Integer.parseInt(mr.getParameter("aniPrice"));
		String speChk = mr.getParameter("speChk");
		
		AniInfo pi = new AniInfo();
		pi.setAniPic1(aniPic1);
		pi.setAniPic2(aniPic2);
		pi.setAniName1(aniName1);
		pi.setAniName2(aniName2);
		pi.setAniHello1(aniHello1);
		pi.setAniHello2(aniHello2);
		pi.setAniHello3(aniHello3);
		pi.setAniMaxStat1(aniMaxStat1);
		pi.setAniMaxStat2(aniMaxStat2);
		pi.setAniPrice(aniPrice);
		pi.setSpeChk(speChk);
		
		AniInfoDao pd = AniInfoDao.getInstance();
		result = pd.insert(pi);
		return result;
	}
	public int insert2(HttpServletRequest request) {
		int result = 0; MultipartRequest mr = null;
		String save = "/upload";
		String real = request.getSession()
			.getServletContext().getRealPath(save);
		int maxSize = 10 * 1024 * 1024;
		try { mr = new MultipartRequest(request, real, maxSize,
				"utf-8",new DefaultFileRenamePolicy());
		} catch (IOException e) {
			System.out.println(e.getMessage()); }
		String itemPic = mr.getFilesystemName("itemPic");
		String itemName = mr.getParameter("itemName");
		int itemStat = Integer.parseInt(mr.getParameter("itemStat"));
		int itemPrice = Integer.parseInt(mr.getParameter("itemPrice"));
		
		ItemInfo i = new ItemInfo();
		i.setItemPic(itemPic);
		i.setItemName(itemName);
		i.setItemStat(itemStat);
		i.setItemPrice(itemPrice);		
		
		ItemInfoDao ii = ItemInfoDao.getInstance();
		result = ii.insert(i);
		return result;
	}
	public int insert3(HttpServletRequest request) {
		int result = 0; MultipartRequest mr = null;
		String save = "/upload";
		String real = request.getSession()
			.getServletContext().getRealPath(save);
		int maxSize = 10 * 1024 * 1024;
		try { mr = new MultipartRequest(request, real, maxSize,
				"utf-8",new DefaultFileRenamePolicy());
		} catch (IOException e) {
			System.out.println(e.getMessage()); }
		String mapItemPic = mr.getFilesystemName("mapItemPic");
		String mapItemName = mr.getParameter("mapItemName");
		int mapItemPrice = Integer.parseInt(mr.getParameter("mapItemPrice"));
		
		MapItemInfo mii = new MapItemInfo();
		
		mii.setMapItemPic(mapItemPic);
		mii.setMapItemName(mapItemName);
		mii.setMapItemPrice(mapItemPrice);		
		
		MapItemInfoDao miid= MapItemInfoDao.getInstance();
		result = miid.insert(mii);
		return result;
	}
}