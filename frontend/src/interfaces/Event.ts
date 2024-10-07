export interface IEvent {
	id: number;
	title: string;
	startDate: string;
	endDate: string;
	price: number;
	status: EventStatus;
}

export enum EventStatus {
	STARTED,
	COMPLETED,
	PAUSED,
}
