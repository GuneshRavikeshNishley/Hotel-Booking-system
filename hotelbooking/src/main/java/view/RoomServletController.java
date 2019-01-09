package view;

import domain.Room;
import domain.RoomType;
import exception.EntityNotFoundException;
import service.RoomService;
import service.Service;
import view.util.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class RoomServletController extends BaseController {
    private Service<Room> service;

    public void init() {
        service = new RoomService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            String action = extractAction(request);

            switch (action) {
                case "/new":
                    System.out.println("new");
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertRoom(request, response);
                    break;
                case "/delete":
                    deleteRoom(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateRoom(request, response);
                    break;
                default:
                    listRoom(request, response);
                    break;
            }
        } catch (ServletException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

    }


    private String extractAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        } else {
            return pathInfo;
        }
    }


    private void listRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Room> listRoom = service.findAll();
        request.setAttribute("listRoom", listRoom);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/room/RoomList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("pages/room/RoomForm.jsp");
        request.setAttribute("types", RoomType.values());
        request.setAttribute("isNew", true);
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String id = request.getParameter("id");
            Room existingRoom = service.findById(id);
            RequestDispatcher dispatcher = request
                    .getRequestDispatcher("/pages/room/RoomForm.jsp");
            request.setAttribute("types", RoomType.values());
            request.setAttribute("room", existingRoom);
            request.setAttribute("isEdit", true);
            dispatcher.forward(request, response);
        } catch (EntityNotFoundException e) {
            request.setAttribute("message", e.getMessage());
            listRoom(request, response);
        }

    }

    private void insertRoom(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Room room = null;


        try {
            String id = request.getParameter("id");
            String typeStr = request.getParameter("type");


            room = new Room(id, RoomType.valueOf(typeStr));

            service.add(room);
            request.setAttribute("message", Message.buildSuccessMessage("Room added successfully"));
            listRoom(request, response);
        } catch (Exception e) {
            request.setAttribute("room", room);
            request.setAttribute("message", processException(e));
            request.setAttribute("isNew", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/room/RoomForm.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void updateRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Room room = null;
        try {
            String id = request.getParameter("id");
            String typeStr = request.getParameter("type");

             room = new Room(id,
                     RoomType.valueOf(typeStr));

            service.modify(room);
            request.setAttribute("message", Message.buildSuccessMessage("Room updated successfully"));
            listRoom(request, response);
        } catch (Exception e) {
            request.setAttribute("room", room);
            request.setAttribute("message", processException(e));
            request.setAttribute("isEdit", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/room/RoomForm.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void deleteRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            service.remove(id);
            request.setAttribute("message", Message.buildSuccessMessage("Room deleted successfully"));
            listRoom(request, response);
        } catch (Exception e) {
            request.setAttribute("message", processException(e));
            listRoom(request, response);
        }

    }

}
