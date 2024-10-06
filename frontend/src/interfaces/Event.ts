export interface IEvent {
	id: number;
	tittle: string;
	startDate: string;
	endDate: string;
	price: number;
	status: EventStatus;
}

export enum EventStatus {
	Started,
	Completed,
	Paused,
}
