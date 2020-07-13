package br.gov.sp.fatec.projetoweb.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.gov.sp.fatec.projetoweb.dao.UsuarioDao;
import br.gov.sp.fatec.projetoweb.entity.Usuario;

public class AuthFilter implements Filter {
    
    private ServletContext context;
    private String realm = "PROTECTED";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        this.context.log("Filtro acessado!");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        


        UsuarioDao usuarioDao= new UsuarioDao();

            

        // Verifica se tem o header Authorization
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            // Divide o conteúdo do header por espacos
            StringTokenizer st = new StringTokenizer(authHeader);
            // Se possui conteúdo
            if (st.hasMoreTokens()) {
                String basic = st.nextToken();
                // Verifica se possui o prefixo Basic
                if (basic.equalsIgnoreCase("Basic")) {
                    try {
                        // Extrai as credenciais (Base64)
                        String credentials = 
                                new String(
                                    Base64.getDecoder()
                                        .decode(st.nextToken()));
                        this.context.log("Credentials: " + credentials);
                        // Separa as credenciais em usuario e senha
                        Integer p = credentials.indexOf(":");
                        
                        if (p != -1) {
                            String _username = 
                                    credentials.substring(0, p).trim();
                            String _password = 
                                    credentials.substring(p + 1).trim();
                            // Se nao bate com configuracao retorna erro
                            Usuario user = usuarioDao.usernameUsuario(_username); 
                            System.out.println("\n\n\n\n\n\nUsername enviado:"+_username);
                            System.out.println("Senha enviado:"+_password);
                            System.out.println("Username Banco:"+user.getLogin());
                            System.out.println("Senha Banco:"+user.getSenha()+"\n");
                            System.out.println("Usuario: "+user+"\n");
                            
                                                       
                            if(user!=null && user.getSenha().equals(_password)){
                                System.out.println("Entrei primeiro if\n");
                                if (!(request.getMethod().equals("DELETE") || request.getMethod().equals("PUT")))  {
                                    System.out.println("Entrei 2 if\n");
                                    chain.doFilter(req, res);
                                }else if(_username.equals("admin") && user.getSenha().equals(_password)){
                                    System.out.println("Entrei 3 if\n");
                                    chain.doFilter(req, res);
                                }else{
                                    response.sendError(403);
                                }                                
                            }else{
                                unauthorized(response, "Bad credentials");      
                            }
                        } else {
                            unauthorized(response, 
                            "Invalid authentication token");
                        }
                            
                    } catch (UnsupportedEncodingException e) {
                        throw new Error("Couldn't retrieve authentication", e);
                    }
                }
            }
        } else {
            unauthorized(response);
        }

    }

    @Override
    public void destroy() {
        // Aqui pode-se desalocar qualquer recurso
        
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.context = config.getServletContext();
        this.context.log("Filtro inicializado!");
    }

    private void unauthorized(HttpServletResponse response, String message) 
            throws IOException {
        response.setHeader("WWW-Authenticate", "Basic realm=\"" + realm + "\"");
        response.sendError(401, message);
    }

    private void unauthorized(HttpServletResponse response) throws IOException {
        unauthorized(response, "Unauthorized");
    }

}
