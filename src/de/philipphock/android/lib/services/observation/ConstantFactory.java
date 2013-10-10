package de.philipphock.android.lib.services.observation;

public class ConstantFactory {

	private interface ServiceObservableConstants {
		public static final String BROADCAST_SERVICE_STARTED = "de.philipphock.android.lib.services.observation.service_started";
		public static final String BROADCAST_SERVICE_STOPPED = "de.philipphock.android.lib.services.observation.service_stopped";
		public static final String BROADCAST_SERVICE_FORCE_RESEND_STATUS = "de.philipphock.android.lib.services.observation.force_resend_status";
		public static final String BROADCAST_SERVICE_EXTRA_SERVICE_NAME = "de.philipphock.android.lib.services.observation.extra.servicename";
	}
	
	public static String getServiceStartedString(ObservableService service){
		return getServiceStartedString(service.getServiceName());
	}
	
	public static String getServiceStoppedString(ObservableService service){
		return getServiceStoppedString(service.getServiceName());
	}
	
	public static String getForceResendStatusString(ObservableService service){
		return getForceResendStatusString(service.getServiceName());
	}
	
	
	
	public static String getServiceStartedString(String serviceName){
		return ServiceObservableConstants.BROADCAST_SERVICE_STARTED+serviceName;
	}
	
	public static String getServiceStoppedString(String serviceName){
		return ServiceObservableConstants.BROADCAST_SERVICE_STOPPED+serviceName;
	}
	
	public static String getForceResendStatusString(String serviceName){
		return ServiceObservableConstants.BROADCAST_SERVICE_FORCE_RESEND_STATUS+serviceName;
	}
}
