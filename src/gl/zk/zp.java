package gl.zk;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

import com.sun.corba.se.impl.orbutil.concurrent.Mutex;

public class zp {
	private final static String DEFAULT_HOST = "59.151.111.57"; //"192.168.41.7";
	private final static int DEFAULT_PORT = 2181;
	private final static int DEFAULT_TIMEOUT = 30000;
	private static String zkHost = DEFAULT_HOST;
	private static int zkPort = DEFAULT_PORT;
	private static int zkTimeout = DEFAULT_TIMEOUT;
	private static ZooKeeper zk = null;
	  private  Mutex mutex = new Mutex();
	  
	public zp() {
		
		try {
			/*zk = new ZooKeeper(zkHost + ":" + zkPort, zkTimeout, new Watcher() {

				// 监控所有被触发的事件
				public void process(WatchedEvent event) {
					if("NodeChildrenChanged".equals(event.getType())){
						
					}
					System.out.println("已经触发了[" + event.getType() + "]事件！");
				}
			});*/
			
			
			zk = new ZooKeeper(zkHost + ":" + zkPort, zkTimeout,null);
			
			
			FileInputStream fis = new FileInputStream("c:\\sp_delete_less100.sql");
			
			byte[] b = new byte[100000];
			
			int readnum = fis.read(b);
			if( readnum == b.length) {
				System.out.println("buffer is small");
				return;
			}
			
			byte[] desb = new byte[readnum];
			System.arraycopy(b,0, desb,0,readnum);
			
			try {
				zk.setData("/datanode",desb,-1);
			} catch (KeeperException | InterruptedException e) {
				System.out.print(e.getMessage());
			}
			
			
			/*byte[] b = zk.getData("/conf",false,null);
			List<String> list = ByteArray2List(b);
			for(String str:list){
				
				System.out.println("str:"+str);
				
			}*/
			
			//String localhost = InetAddress.getLocalHost().getHostAddress();

			//zk.create("/znode/"+localhost,"ip".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
			//zk.create("/zpnode/"+localhost,"ip".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
			
			//zk.create("/errornode/"+localhost,"error is xxxx".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
			
			
			/*byte[] leader = null;
			byte[] leader2 = null;
			try{
				leader = zk.getData("/leader",false,null);
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			try{
				
				leader2 = zk.getData("/leader2",false,null);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(leader != null){
				zk.delete("/leader",-1);
			}
			
			if(leader2 != null){
				zk.delete("/leader2",-1);
			}
			
			System.out.println(zk.getChildren("/",true)); 
			
			zk.create("/leader","/leader".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
			
			System.out.println(zk.getChildren("/",true));
			zk.create("/leader2","/leader2".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT); 
			
			//zk.delete("/leader",-1);
			
			//Thread.sleep(2000);
			
			//findLeader();
			 
			
			
			System.out.println(new String(zk.getData("/leader", false, null)));*/ 
			
			//System.out.println("start sleep");
			//Thread.sleep(20000);
			
			zk.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("done");

	}
	

}
