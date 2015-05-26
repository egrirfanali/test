package com.strendent.tutorsu;




import com.parse.Parse;

import android.app.Application;

public class TutorsUApplication extends Application {

	 public static final String TAG = "TutorsU";
	 
	@Override
	public void onCreate() {
		super.onCreate();
		Parse.enableLocalDatastore(this);
		Parse.initialize(this, "seelmA8JpbqmYqYFEGAJ4CGRII1US0xSToAwPPXZ", "knS8cOSNmViSzuL5SrAp27JwczHTBn9JMGQ5LuOE");
	
//		   Parse.initialize(this, 
//			        "eb5cbf2f0fc36a3cbd83ff7bc7500b98",
//			        "knS8cOSNmViSzuL5SrAp27JwczHTBn9JMGQ5LuOE"
//			    );
	}

	
	
}
