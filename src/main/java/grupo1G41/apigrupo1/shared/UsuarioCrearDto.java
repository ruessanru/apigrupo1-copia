
package grupo1G41.apigrupo1.shared;

import java.io.Serializable;

public class UsuarioCrearDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;
    private String userEmail;
    private String password;


    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    
}
