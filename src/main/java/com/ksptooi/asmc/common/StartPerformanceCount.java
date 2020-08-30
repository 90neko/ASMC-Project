package com.ksptooi.asmc.common;

import java.text.DecimalFormat;

import com.ksptooi.asmc.main.Asmc;

public class StartPerformanceCount implements Runnable{

	double time=0.0;
	Thread TH=null;
	
	//SAC Timer初始化
	public StartPerformanceCount(){
		time=0.0;
		TH=new Thread(this);	
		
		Asmc.getLogger().info("开始性能计数");
		
	}
	
	public void Timer(){
		
		Thread TH=new Thread(this);
		TH.start();
		
	}
	
	@SuppressWarnings("deprecation")
	public String StopTimer(){

		double time=this.time;
		
		TH.stop();
		
		DecimalFormat df=new DecimalFormat("######0.00");
		
		this.time=0.0;	
		return df.format(time);
		
	}
	
	
	@Override
	public void run() {
		
		while(true){

			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			time=time+0.1;
			
		}
		
	}

}
