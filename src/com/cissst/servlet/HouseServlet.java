package com.cissst.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cissst.entity.House;
import com.cissst.service.IHouseService;
import com.cissst.service.impl.HouseServiceImpl;

public class HouseServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		IHouseService ihs = new HouseServiceImpl();
		if("houseList".equals(action)){//跳转至业主房屋租赁信息界面
			List<House> list =  ihs.findAllHouse();
			request.setAttribute("houses", list);
			RequestDispatcher rd = request.getRequestDispatcher("house/house-list.jsp");
			rd.forward(request, response);
		
		}else if("houseAdd".equals(action)){//增加房屋租赁信息
			
//			int id = Integer.parseInt(request.getParameter("id"));
			String num = request.getParameter("num");
			String dep = request.getParameter("dep");
			String type = request.getParameter("type");
			String area = request.getParameter("area");
			String sell = request.getParameter("sell");
			String unit = request.getParameter("unit");
			String floor = request.getParameter("floor");
			String direction = request.getParameter("direction");
			String memo = request.getParameter("memo");
			String ownerid = request.getParameter("ownerid");
            //保存参数
			House h = new House();
//			h.setId(id);
			h.setNum(num);
			h.setDep(dep);
			h.setType(type);
			h.setArea(area);
			h.setSell(sell);
			h.setUnit(unit);
			h.setFloor(floor);
			h.setDirection(direction);
			h.setMemo(memo);
			h.setOwnerid(ownerid);
			
			ihs.add(h);
			//参数保存后供列表信息界面使用，并将新的租赁信息展示出来
			response.sendRedirect("house?action=houseList");
		}else if("findByOwnerid".equals(action)){//查询所有业主编号
			String ownerid = request.getParameter("ownerid");
			List<House>  list = ihs.findByOwnerid(ownerid);	
			//将由物业人员发布的租赁信息用列表存储
			request.setAttribute("houses", list);
			RequestDispatcher rd = request.getRequestDispatcher("house/user-house-list.jsp");
			rd.forward(request, response);
		}else if("houseEdit".equals(action)){//编辑房屋租赁信息
			int id = Integer.parseInt(request.getParameter("id"));
			String num = request.getParameter("num");
			String dep = request.getParameter("dep");
			String type = request.getParameter("type");
			String area = request.getParameter("area");
			String sell = request.getParameter("sell");
			String unit = request.getParameter("unit");
			String floor = request.getParameter("floor");
			String direction = request.getParameter("direction");
			String memo = request.getParameter("memo");
			String ownerid = request.getParameter("ownerid");
			House h = new House();
			//保存编辑后的参数
			h.setId(id);
			h.setNum(num);
			h.setDep(dep);
			h.setType(type);
			h.setArea(area);
			h.setSell(sell);
			h.setUnit(unit);
			h.setFloor(floor);
			h.setDirection(direction);
			h.setMemo(memo);
			h.setOwnerid(ownerid);
            //更新编辑信息
			ihs.update(h);
			//参数保存后供列表信息界面使用，并将编辑后的租赁信息展示出来
			response.sendRedirect("house?action=houseList");
		}else if("houseDelete".equals(action)){//删除租赁信息
			String id = request.getParameter("id");
			ihs.delete(id);
			response.sendRedirect("house?action=houseList");
		}else if("findById".equals(action)){//查询指定业主编号的租赁信息，并跳转至编辑界面
			String id = request.getParameter("id");
			House h = ihs.findById(id);

			request.setAttribute("house",h);
			RequestDispatcher rd = request.getRequestDispatcher("house/house-edit.jsp");
			rd.forward(request, response);
			
		}
	}
	
}
