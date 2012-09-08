package pl.mobilization.speakermeter.downloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import pl.mobilization.speakermeter.R;
import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

public abstract class AbstractDownloader<T> implements Future {
	private static final String TAG = AbstractDownloader.class.getName();

	private ReentrantLock lock = new ReentrantLock();

	private boolean isCancelled;

	private T result;

	private boolean isDone;

	private Condition isDoneC;

	public AbstractDownloader() {
		isDoneC = lock.newCondition();
	}

	public void addCookies(URI uri, CookieStore cookieStore) {
	}

	public abstract URI createURI();

	private void exceptionHandler(Exception e) {
		// final String exceptionString = getExceptionString(e);
		// getEnclosingClass().runOnUiThread(new Runnable() {
		//
		// public void run() {
		// Toast.makeText(getEnclosingClass(), exceptionString,
		// Toast.LENGTH_LONG).show();
		// }
		// });

	}

	public abstract void cleanUp();

	private String extractPageAsString(HttpResponse response)
			throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		response.getEntity().writeTo(out);
		out.close();
		String responseString = out.toString();
		return responseString;
	}

	//
	// private String getExceptionString(Exception e) {
	// if (e instanceof IOException) {
	// return getResources().getString(
	// R.string.problem_connection, e.getLocalizedMessage());
	// }
	// if (e instanceof JsonParseException) {
	// return getEnclosingClass().getResources().getString(
	// R.string.problem_json, e.getLocalizedMessage());
	// }
	// return getEnclosingClass().getResources().getString(
	// R.string.problem_uknown, e.getLocalizedMessage());
	// }

	public abstract void processAnswer(String json) throws JsonSyntaxException;

	public void run() {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		try {
			URI uri = createURI();
			HttpRequestBase request = new HttpGet(uri);
			request.addHeader("Accept", "application/json");

			CookieStore cookieStore = httpclient.getCookieStore();
			addCookies(uri, cookieStore);

			httpclient.setCookieStore(cookieStore);

			response = httpclient.execute(request);
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
				// Closes the connection when status is not OK
				response.getEntity().getContent().close();
				throw new HttpException(statusLine.getReasonPhrase());
			}

			String json = extractPageAsString(response);

			processAnswer(json);
		} catch (Exception e) {
			Log.e(TAG, "Exception occured during processing response", e);
			exceptionHandler(e);
		} finally {
			cleanUp();
		}

	}

	public boolean cancel(boolean mayInterruptIfRunning) {
		Thread.currentThread().interrupt();
		this.isCancelled = true;
		return false;
	}

	public T get() throws InterruptedException, ExecutionException {
		isDoneC.await();
		isDoneC.
		return result;
	}

	public T get(long timeout, TimeUnit unit) throws InterruptedException,
			ExecutionException, TimeoutException {

		try {
			lock.lock();
			if (isDoneC.await(timeout, unit))
			return result;

		} finally {
			lock.unlock();
		}
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public boolean isDone() {
		return isDone;
	}

	protected void setResult(T result) {
		try {
			lock.lock();
			isDoneC.signalAll();
			this.result = result;
		} finally {
			lock.unlock();
		}

	}
}
