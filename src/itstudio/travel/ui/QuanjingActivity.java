package itstudio.travel.ui;

import itstudio.travel.R;
import itstudio.travel.widget.Ball;
import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class QuanjingActivity extends Activity {

	GLSurfaceView mGlSurfaceView;
	Ball mBall;
	private float mPreviousY;
	private float mPreviousX;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mGlSurfaceView = new GLSurfaceView(this);
		mGlSurfaceView.setEGLContextClientVersion(2);
		mBall = new Ball(this,R.drawable.pic_quanjiang);
		mGlSurfaceView.setRenderer(mBall);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(mGlSurfaceView);
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		float y = e.getY();
		float x = e.getX();
		switch (e.getAction()) {
		case MotionEvent.ACTION_MOVE:
			float dy = y - mPreviousY;// 计算触控笔Y位移
			float dx = x - mPreviousX;// 计算触控笔X位移
			mBall.yAngle += dx * 0.1f;// 设置填充椭圆绕y轴旋转的角度
			mBall.xAngle += dy * 0.1f;// 设置填充椭圆绕x轴旋转的角度
		}
		mPreviousY = y;// 记录触控笔位置
		mPreviousX = x;// 记录触控笔位置
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (null != mGlSurfaceView) {
			mGlSurfaceView.onResume();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (null != mGlSurfaceView) {
			mGlSurfaceView.onPause();
		}
	}

}
