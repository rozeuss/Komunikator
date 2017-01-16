package tasks;

import controllers.SplashController;

public class SplashScreen implements Runnable {
		private boolean isDataLoaded;
		private SplashController splashController;
		
		public SplashScreen(SplashController splashController) {
			this.splashController = splashController;
			this.isDataLoaded = false;
		}
		
		public void setIsDataLoaded(boolean isDataLoaded) {
			this.isDataLoaded = isDataLoaded;
		}

		@Override
		public void run() {
			while(!isDataLoaded) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			splashController.launchMainView();
		}
	}