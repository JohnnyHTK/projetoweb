package br.gov.sp.fatec.projetoweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.gov.sp.fatec.projetoweb.dao.DubleDao;
import br.gov.sp.fatec.projetoweb.entity.Duble;



public class DubleController extends HttpServlet {

    private static final long serialVersionUID = 1L;

@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));

        DubleDao dubleDao = new DubleDao();
        Duble duble = dubleDao.buscar(id);

        ObjectMapper mapper = new ObjectMapper();
        String dubleJson = mapper.writeValueAsString(duble);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(200);
        PrintWriter out = resp.getWriter();
        out.print(dubleJson);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        try{
        ObjectMapper mapper = new ObjectMapper();
        Duble duble = mapper.readValue(req.getReader(), Duble.class);

        DubleDao dubleDao = new DubleDao();
        dubleDao.salvar(duble);

        String dubleJson = mapper.writeValueAsString(duble);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        resp.setStatus(201);
        String location = req.getServerName() + ":" + req.getServerPort() 
                + req.getContextPath() + "/Duble?id=" + duble.getId();
        resp.setHeader("Location", location);
        PrintWriter out = resp.getWriter();
        out.print(dubleJson);
        out.flush();
        }
        catch(Exception e) {
            resp.setStatus(400);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
        try{
        ObjectMapper mapper = new ObjectMapper();
        Duble duble = mapper.readValue(req.getReader(), Duble.class);
        DubleDao dubleDao = new DubleDao();
        dubleDao.excluir(duble.getId());
        
        resp.setStatus(201);
        
        }
        catch(Exception e) {
            resp.setStatus(400);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException {
        try{
        ObjectMapper mapper = new ObjectMapper();
        Duble duble = mapper.readValue(req.getReader(), Duble.class);
        DubleDao dubleDao = new DubleDao();
        Duble dubleInstancia = dubleDao.update(duble); 
        dubleDao.salvar(dubleInstancia);
        resp.setStatus(204);
        
        }
        catch(Exception e) {
            resp.setStatus(400);
        }

    }
}