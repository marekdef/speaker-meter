package pl.mobilization.speakermeter.speakers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import pl.mobilization.speakermeter.R;
import pl.mobilization.speakermeter.dao.Speaker;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.common.base.Strings;
import com.google.common.collect.Iterators;

public class SpeakerSetAdapter extends BaseAdapter {
	private static final String TAG = SpeakerSetAdapter.class.getSimpleName();
	LayoutInflater inflater = null;
	java.text.DateFormat timeInstance = new SimpleDateFormat("kk:mm");

	private Set<Speaker> backingSet = new TreeSet<Speaker>(
			new Comparator<Speaker>() {

				public int compare(Speaker lhs, Speaker rhs) {
					int dateCompare = lhs.getStart_time().compareTo(
							rhs.getStart_time());
					if (dateCompare != 0)
						return dateCompare;
					int venueCompare = lhs.getVenue().compareTo(rhs.getVenue());
					if (venueCompare != 0)
						return venueCompare;
					return lhs.compareTo(rhs);
				}
			});

	public SpeakerSetAdapter(Context context, Collection<Speaker> list) {
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		timeInstance.setTimeZone(TimeZone.getTimeZone("UTC"));

		backingSet.addAll(list);
	}

	public int getCount() {
		return backingSet.size();
	}

	public Speaker getItem(int position) {
		return Iterators.get(backingSet.iterator(), position);
	}

	public long getItemId(int position) {
		return getItem(position).getId();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.speaker_info, null);
		}

		Speaker speaker = getItem(position);

		View speakerInfo = convertView.findViewById(R.id.speaker_info);
		TextView textViewSpeaker = (TextView) convertView
				.findViewById(R.id.textViewSpeaker);
		TextView textViewPresentation = (TextView) convertView
				.findViewById(R.id.textViewPresentation);
		TextView textViewRoom = (TextView) convertView
				.findViewById(R.id.textViewRoom);
		TextView textViewTime = (TextView) convertView
				.findViewById(R.id.textViewTime);

		textViewSpeaker.setText(speaker.getName());
		textViewPresentation.setText(speaker.getPresentation());
		String venue = speaker.getVenue();
		textViewRoom.setText(Strings.isNullOrEmpty(venue) ? "" : inflater
				.getContext().getString(R.string.room, venue));

		Log.d(TAG, speaker + speaker.getStart_time().toGMTString());
		Log.d(TAG, speaker + speaker.getEnd_time().toGMTString());
		
		//This is tricky thing. Server stores times in UTC not in Europe/Warsaw need to convert now to UTC now and then check
		DateTime dtPolishTime = new DateTime();
	    DateTime dtUTC = dtPolishTime.withZoneRetainFields(DateTimeZone.UTC);
	    Date nowInUTC = dtUTC.toDate();

		if (nowInUTC.after(speaker.getStart_time())
				&& nowInUTC.before(speaker.getEnd_time())) {
			speakerInfo.setBackgroundColor(speakerInfo.getResources().getColor(
					R.color.soldier));
		} else {
			speakerInfo.setBackgroundColor(speakerInfo.getResources().getColor(
					android.R.color.black));
		}

		CharSequence startTime = timeInstance.format(speaker.getStart_time());
		CharSequence endTime = timeInstance.format(speaker.getEnd_time());

		textViewTime.setText(String.format("%s-%s", startTime, endTime));

		convertView.setTag(speaker);
		return convertView;
	}

	public void addItem(Speaker speaker) {
		backingSet.add(speaker);
		notifyDataSetChanged();
	}

	public void addItems(Speaker[] speakers) {
		for (Speaker speaker : speakers) {
			backingSet.add(speaker);
		}
		notifyDataSetChanged();
	}

	@Override
	public boolean isEnabled(int position) {
		return getItem(position).isVisible();
	}
}
