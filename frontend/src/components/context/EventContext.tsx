import { createContext, Dispatch, SetStateAction, useEffect, useState } from "react";
import { useNotification } from "../../hooks/useNotification";
import { useRequest } from "../../hooks/useRequest";
import { IEvent } from "../../interfaces/Event";
import { apiPath } from "../../services/api";

type EventContextData = {
	events: IEvent[];
	setEvents: Dispatch<SetStateAction<IEvent[]>>;

	setSearchTerm: Dispatch<SetStateAction<string | null>>;
	loading: boolean;

	getEvents: () => Promise<void>;
	deleteEvent: (id: number) => Promise<boolean>;
	editEvent: (event: IEvent) => Promise<boolean>;
	createEvent: (event: IEvent) => Promise<boolean>;
};

export const EventContext = createContext<EventContextData>({} as EventContextData);

type EventProviderProps = {
	children: React.ReactNode;
};

export default function EventProvider({ children }: EventProviderProps) {
	const { loading, request } = useRequest();
	const notify = useNotification();

	const [events, setEvents] = useState<IEvent[]>([]);
	const [filteredEvents, setFilteredEvents] = useState(events);
	const [searchTerm, setSearchTerm] = useState<string | null>(null);

	useEffect(() => {
		if (!searchTerm) {
			setFilteredEvents(events);
			return;
		}

		const filtered = events.filter((x) => x.title.toLowerCase().includes(searchTerm.toLowerCase()));
		setFilteredEvents(filtered);
	}, [searchTerm]);

	useEffect(() => setFilteredEvents(events), [events]);

	const getEvents = async () => {
		const { data, success } = await request.get<IEvent[]>(apiPath.events);
		if (success && data) setEvents(data);
	};

	const deleteEvent = async (id: number): Promise<boolean> => {
		const { success } = await request.delete<IEvent>(`${apiPath.events}/${id}`);
		if (!success) return false;

		notify.success("Event deleted successfully!");
		getEvents();

		return true;
	};

	const editEvent = async (event: IEvent): Promise<boolean> => {
		const { success } = await request.put<IEvent>(`${apiPath.events}/${event.id}`, event);
		if (!success) return false;

		notify.success("Event updated successfully!");
		getEvents();

		return true;
	};

	const createEvent = async (event: IEvent): Promise<boolean> => {
		const { success } = await request.post<IEvent>(apiPath.events, event);
		if (!success) return false;

		notify.success("Event created successfully!");
		getEvents();

		return true;
	};

	return (
		<EventContext.Provider
			value={{ events: filteredEvents, setEvents, loading, setSearchTerm, getEvents, deleteEvent, editEvent, createEvent }}
		>
			{children}
		</EventContext.Provider>
	);
}
