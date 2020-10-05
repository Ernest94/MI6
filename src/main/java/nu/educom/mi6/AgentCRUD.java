package nu.educom.mi6;

import java.sql.SQLException;
import java.util.HashMap;

public class AgentCRUD {

    private CRUD crud;
    
    public AgentCRUD() {
        this.crud = new CRUD();
    }
    
    public Agent readAgentByServiceNumber(int serviceNumber) {
        String sql = "SELECT secret_code,active,license_to_kill FROM agents WHERE service_number=? ";
        String[] params = {Integer.toString(serviceNumber)};
        Agent agent = new Agent();
        agent.setServiceNumber(serviceNumber);
        HashMap<String,Object> output = null;
        try {
            output = crud.readOneRow(sql,params);
        } catch (SQLException e) {
        }
        if (output != null) {
            agent.setSecretCode(output.get("secret_code"));
            agent.setActivity(output.get("active"));
            agent.setLicenseToKill(output.get("license_to_kill"));
        }
        return agent;

    }
}
