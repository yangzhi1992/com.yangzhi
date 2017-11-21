package crawler;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by gavinding on 2017/5/24.
 */
public class IpModel implements Comparator<IpModel>, Serializable {

    
    /**  */
    private static final long serialVersionUID = 3190739319796120349L;
    /**
     * ip地址
     **/
    private String ip;
    /**
     * 端口号
     **/
    private Integer           port;
    /**
     * 校验速度 访问网站响应时间毫秒数
     */
    private Long              speed;
    /**
     * 最后校验时间
     */
    private Long              lastValidate;
    /**
     * 错误次数
     */
    private Integer           wrongTimes;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Long getSpeed() {
        return speed;
    }

    public void setSpeed(Long speed) {
        this.speed = speed;
    }

    public Long getLastValidate() {
        return lastValidate;
    }

    public void setLastValidate(Long lastValidate) {
        this.lastValidate = lastValidate;
    }

    public Integer getWrongTimes() {
        return wrongTimes == null ? 0 : wrongTimes;
    }

    public void setWrongTimes(Integer wrongTimes) {
        this.wrongTimes = wrongTimes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
        result = prime * result + port;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        IpModel other = (IpModel) obj;
        if (ip == null) {
            if (other.ip != null)
                return false;
        } else if (!ip.equals(other.ip))
            return false;
        if (port != other.port)
            return false;
        return true;
    }

    @Override
    public int compare(IpModel o1, IpModel o2) {

        if (o1 != null && o2 != null) {

            return (int) (o1.speed - o2.speed);
        } else {
            return 0;
        }
    }

}
