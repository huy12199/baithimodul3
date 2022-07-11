package controller;

import dao.DepartmentDao;
import dao.StaffDao;
import model.Department;
import model.Staff;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = "/staff")
public class StaffServlet extends HttpServlet {
    private StaffDao staffDao = new StaffDao();
    private DepartmentDao departmentDao = new DepartmentDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(req, resp);
                break;
            case "edit":
                showEditForm(req,resp);
                break;
            case "delete":
                showDeleteForm(req,resp);
                break;
            default:
                showStaff(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createStaff(req, resp);
                break;
            case "edit":
                updateStaff(req,resp);
                break;
            case "delete":
                deleteStaff(req,resp);
                break;
            case "search":
                String searchName = req.getParameter("searchName");
                if (searchName==""){
                    showStaff(req, resp);
                }else {
                    List<Staff> staffs = staffDao.getSearch(searchName);
                    req.setAttribute("staffs", staffs);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("view/staff.jsp");
                    dispatcher.forward(req, resp);
                }
                break;
            default:
                break;
        }
    }

    public void showStaff(HttpServletRequest request, HttpServletResponse response) {
        List<Staff> staffs = staffDao.getAll();
        request.setAttribute("staffs", staffs);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/staff.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        List<Department> departments = departmentDao.getAll();
        request.setAttribute("department", departments);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Staff staff =staffDao.findById(id);
        RequestDispatcher dispatcher;
        if (staff == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("staff", staff);
            dispatcher = request.getRequestDispatcher("view/edit.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Staff staff = staffDao.findById(id);
        RequestDispatcher dispatcher;
        if (staff == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("staff", staff);
            dispatcher = request.getRequestDispatcher("view/delete.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createStaff(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        LocalDate birth = LocalDate.parse(request.getParameter("birth"));
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        int idD = Integer.parseInt(request.getParameter("departments"));
        Staff st = new Staff(id, name, birth, address, phone, email, departmentDao.findById(idD));
        staffDao.create(st);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/create.jsp");
        request.setAttribute("message", "New staff was created");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateStaff(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        LocalDate birth = LocalDate.parse(request.getParameter("birth"));
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        int idD = Integer.parseInt(request.getParameter("departments"));
        Staff staff = new Staff(id, name, birth, address, phone, email, departmentDao.findById(idD));
        RequestDispatcher dispatcher;
        if (staff == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {

            staffDao.edit(id,staff);
            request.setAttribute("staff", staff);
            request.setAttribute("message", "Staff information was updated");
            dispatcher = request.getRequestDispatcher("view/edit.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deleteStaff(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Staff staff = staffDao.findById(id);
        RequestDispatcher dispatcher;
        if (staff == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
           staffDao.delete(id);
            try {
                response.sendRedirect("/staff");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
