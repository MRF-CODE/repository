package com.hzyc.ccs.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.hzyc.ccs.model.Boss;
import com.hzyc.ccs.model.StoreSign;
import com.hzyc.ccs.model.Users;
import com.hzyc.ccs.service.UserSer;
import com.hzyc.ccs.tools.Fenye;
import com.hzyc.ccs.tools.JDBCTools;
import com.hzyc.ccs.tools.MD5;
import com.hzyc.ccs.tools.StoreNumToName;

@Controller
public class UserCon {
	@Autowired
	UserSer us;
	JDBCTools jt = new JDBCTools();
	//分页方法
	public Fenye fenye(int Allrow,String nowPage){
		Fenye fenye = new Fenye();
		int pageSize = 3;
		Integer lastPage = Allrow % pageSize == 0 ? Allrow  / pageSize : Allrow 
				/ pageSize + 1;
		
		
		if(nowPage == null || nowPage.equals("") || nowPage.equals("null")){
			nowPage = "1";
		}
		
		if(Integer.parseInt(nowPage) > lastPage){
			nowPage = lastPage.toString();
		}
		if(Integer.parseInt(nowPage) < 1){
			nowPage = "1";
		}
		int now = Integer.valueOf(nowPage) - 1;
		int maxPage = now*pageSize + pageSize;
		if(now*pageSize + pageSize > Allrow){
			maxPage = Allrow;
		}
		fenye.setStartPage(now*pageSize);
		fenye.setLastPage(lastPage);
		fenye.setNowPage(Integer.parseInt(nowPage));
		fenye.setMaxPage(maxPage);
		return fenye;
	}
	//用户登录
	@RequestMapping("/load.action")
	public ModelAndView Login(Boss b,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		request.getSession().setAttribute("uname", b.getUname());
		Users u = new Users();
		u.setUname(b.getUname());
		MD5 md = new MD5();
		u.setUserpw(md.getMD5(b.getUserpw()));
		if(us.Login(b)!=null){
			mav.setViewName("index.jsp");
			request.getSession().setAttribute("storeName","admin");
		}else if(us.managerLogin(u) !=null && us.managerLogin(u).size()>0){
			
			String storeId = us.managerLogin(u).get(0).getStoreName();
			String storeName = us.selStoreById(storeId);
			System.out.println(storeName+"1111111111111111");
			request.getSession().setAttribute("storeName", storeName);
			mav.setViewName("index.jsp");
		}else{
			mav.setViewName("login.jsp");
		}
		return mav;
	}
	@RequestMapping("destorySession.action")
	public String destorySession(HttpServletRequest request){
		request.getSession().invalidate();
		return "login.jsp";
	}
	//用户修改密码
	@RequestMapping("/updPwd.action")
	public ModelAndView updPwd(HttpServletRequest request){
		String bpwd = request.getParameter("bpwd");
		String pwd= request.getParameter("pwd");
		ModelAndView mav =  new ModelAndView();
		String yuanmima = us.selPass(1);
		boolean flag = false;
		boolean cflag = false;
		if(yuanmima.equals(bpwd)){
			cflag = true;
		}
		if(cflag){
			Boss u = new Boss();
			u.setId(1);
			u.setUserpw(pwd);
			flag = us.updPw(u);
		}
		mav.setViewName("systemInstall/personalInstall/main.jsp?compare="+cflag+"&updflag="+flag);
		return mav;
	}
	//添加店名
	@RequestMapping("addstore.action")
	public ModelAndView addStore(StoreSign storeSign){
		ModelAndView mav = new ModelAndView();
		storeSign.setFlag("2");
		int flag = us.addStore(storeSign);
		if(flag !=0){
			mav.setViewName("success.jsp");
		}else{
			
		}
		return mav;
	}
	//按条件查询员工
	@RequestMapping("selUsers.action")
	public ModelAndView selUsers(HttpServletRequest request) throws UnsupportedEncodingException{
		ModelAndView mav = new ModelAndView();
		System.out.println("=========================================");
		String nowPage = request.getParameter("nowPage");
		String storeNum1 = request.getParameter("storeNum");
		String permission1 = request.getParameter("permission");
		System.out.println(storeNum1+permission1);
		String param1 = "";
		String param2 = "";
		if(storeNum1!=null && !storeNum1.equals("")){
			param1 = storeNum1;
		}
		
		if(permission1 !=null){
			permission1 = new String(request.getParameter("permission").getBytes("ISO-8859-1"),"utf-8"); 
			
			String sql = "select rid from roles where rname = '"+permission1+"'";
			ArrayList<HashMap<String,String>> aList = jt.find(sql);
			if(aList != null && aList.size()>0){
				param2 = aList.get(0).get("rid");
			}
		}
		List<Users> uList1= us.selUsers(param1,param2);
		System.out.println(uList1.size());
		//这个for循环的作用是把list里的商店转换成文字，并且把权限添进去
		for(int i = 0;i<uList1.size();i++){
			String storeNum = uList1.get(i).getStoreName();
			int userid = uList1.get(i).getUserid();
			String sql = "SELECT sell_store FROM store_sign WHERE id='"+Integer.parseInt(storeNum)+"'";
			String sql1 = "SELECT rname FROM users,user_role,roles WHERE user_role.userid=users.uname AND user_role.rid = roles.rid and users.userid='"+userid+"'";
			
			ArrayList<HashMap<String,String>> aList = jt.find(sql);
			ArrayList<HashMap<String,String>> aList1 = jt.find(sql1);
			String storeName = aList.get(0).get("sell_store");
			String permission = aList1.get(0).get("rname");
			uList1.get(i).setStoreName(storeName);
			uList1.get(i).setPermission(permission);
		}
		List<Users> uList = new ArrayList<Users>();
		Fenye fenye = fenye(uList1.size(),nowPage);
		for(int i = fenye.getStartPage(); i < fenye.getMaxPage() ;i++){
			uList.add(uList1.get(i));
		}
		mav.addObject("nowPage", fenye.getNowPage());
		mav.addObject("lastPage", fenye.getLastPage());
		mav.addObject("uList", uList);
		mav.addObject("store",storeNum1);
		mav.addObject("permission",permission1);
		mav.setViewName("employee_table.jsp");
		return mav;
		
	}
	//查询一个用户
	@RequestMapping("selOneUser.action")
	public ModelAndView selOneUser(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String userid = request.getParameter("userid");
		Users u = us.selOneUser(userid);
			String storeNum = u.getStoreName();
			int userid1 = u.getUserid();
			String sql = "SELECT sell_store FROM store_sign WHERE id='"+Integer.parseInt(storeNum)+"'";
			String sql1 = "SELECT rname FROM users,user_role,roles WHERE user_role.userid=users.uname AND user_role.rid = roles.rid and users.userid='"+userid1+"'";
			ArrayList<HashMap<String,String>> aList = jt.find(sql);
			ArrayList<HashMap<String,String>> aList1 = jt.find(sql1);
			String storeName = aList.get(0).get("sell_store");
			String permission = aList1.get(0).get("rname");
			u.setStoreName(storeName);
			u.setPermission(permission);
		mav.addObject("user", u);
		mav.setViewName("upload_img.jsp");
		return mav;
	}
	//查询一个用户
	@RequestMapping("selOneUser1.action")
	public ModelAndView selOneUser1(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String userid = request.getParameter("userid");
		Users u = us.selOneUser(userid);
			String storeNum = u.getStoreName();
			int userid1 = u.getUserid();
			String sql = "SELECT sell_store FROM store_sign WHERE id='"+Integer.parseInt(storeNum)+"'";
			String sql1 = "SELECT rname FROM users,user_role,roles WHERE user_role.userid=users.uname AND user_role.rid = roles.rid and users.userid='"+userid1+"'";
			ArrayList<HashMap<String,String>> aList = jt.find(sql);
			ArrayList<HashMap<String,String>> aList1 = jt.find(sql1);
			String storeName = aList.get(0).get("sell_store");
			String permission = aList1.get(0).get("rname");
			u.setStoreName(storeName);
			u.setPermission(permission);
		mav.addObject("user", u);
		mav.setViewName("upload_img1.jsp");
		return mav;
	}
	//删除员工信息
	@RequestMapping("deleteEmployee.action")
	public String deleteEmployee(HttpServletRequest request) throws SQLException{
		String uname = request.getParameter("uname");
		us.deleteEmployee(uname);
		return "selUsers.action";
	}
	//添加身份证照片
	@RequestMapping("uploadimg.action")
	public String uploadImg(HttpServletRequest request,Users users,MultipartFile imageFile) throws IllegalStateException, IOException{
		//ModelAndView mav = selUsers(request);
		String originalFilename = imageFile.getOriginalFilename();
		request.setCharacterEncoding("utf-8");
		if(originalFilename != null && !originalFilename.equals("")){
				String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
				String fileName = users.getUserid() + "_" + new Date().getTime() + suffix;
				String path = request.getSession().getServletContext().getRealPath("/");
 				String finalPathAndName = path +"image/"+fileName;
				//System.out.println(finalPathAndName);
				//目标文件夹(服务器上)
				File target = new File(finalPathAndName);
				//保存图片
				
				//要删除的图片
				String deleteImageName = path + "image/" + users.getImgName();
				File deleteFile = new File(deleteImageName);
				if(deleteFile.exists()){
					target.delete();
				}
				imageFile.transferTo(target);
				
				jt.readImage2DB(finalPathAndName,fileName,users.getUserid());
			}
		
		return "selUsers.action";
	}
	//添加身份证照片
	@RequestMapping("uploadimg1.action")
	public String uploadImg1(HttpServletRequest request,Users users,MultipartFile imageFile) throws IllegalStateException, IOException{
		//ModelAndView mav = selUsers(request);
		String originalFilename = imageFile.getOriginalFilename();
		request.setCharacterEncoding("utf-8");
		if(originalFilename != null && !originalFilename.equals("")){
				String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
				String fileName = users.getUserid() + "_" + new Date().getTime() + suffix;
				String path = request.getSession().getServletContext().getRealPath("/");
 				String finalPathAndName = path +"image/"+fileName;
				//System.out.println(finalPathAndName);
				//目标文件夹(服务器上)
				File target = new File(finalPathAndName);
				//保存图片
				
				//要删除的图片
				String deleteImageName = path + "image/" + users.getImgName();
				File deleteFile = new File(deleteImageName);
				if(deleteFile.exists()){
					target.delete();
				}
				imageFile.transferTo(target);
				
				jt.readImage2DB1(finalPathAndName,fileName,users.getUserid());
			}
		
		return "selUsers.action";
	}
	@RequestMapping("selstore.action")
	public ModelAndView selSotre(){
		ModelAndView mav = new ModelAndView();
		//查询所有店铺
		String[] storeNum = us.selStore();
		StoreNumToName s = new StoreNumToName();
		String[] storeName = s.storeNumToName(storeNum);
		mav.addObject("storeName",storeName);
		mav.addObject("storeNum",storeNum);
		mav.setViewName("emframe.jsp");
		return mav;
	}
}
