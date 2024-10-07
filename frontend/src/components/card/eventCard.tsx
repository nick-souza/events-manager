import { Button, Popconfirm, Tag, Tooltip } from "antd";
import { EventStatus, IEvent } from "../../interfaces/Event";

import { ArrowRightOutlined, DeleteOutlined, EditOutlined } from "@ant-design/icons";
import { useState } from "react";
import "./eventCard.css";

type EventCardProps = {
	event: IEvent;
	deleteEvent: (id: number) => void;
	editEvent: (event: IEvent) => void;
};

export default function EventCard({ event, deleteEvent, editEvent }: EventCardProps) {
	const [visible, setVisible] = useState(false);

	const statusColor = {
		[EventStatus[EventStatus.STARTED]]: "green",
		[EventStatus[EventStatus.COMPLETED]]: "blue",
		[EventStatus[EventStatus.PAUSED]]: "red",
	};

	return (
		<div className="item" onMouseEnter={() => setVisible(true)} onMouseLeave={() => setVisible(false)}>
			<div className="details">
				<div className="title-wrapper">
					<span className="title">{event.title}</span>
				</div>

				<div className="dates">
					<Tooltip title="Start date">
						<span>{event.startDate}</span>
					</Tooltip>

					<ArrowRightOutlined />

					<Tooltip title="End date">
						<span>{event.endDate}</span>
					</Tooltip>
				</div>
			</div>

			<div className="details details-btn">
				<div>
					<Tag>
						<Tooltip title="Price">
							<span className="price">${event.price}</span>
						</Tooltip>
					</Tag>

					<Tag color={statusColor[event.status]}>
						<Tooltip title="Status">
							<span>{event.status}</span>
						</Tooltip>
					</Tag>
				</div>

				{visible && (
					<div className="actions">
						<Tooltip title="Edit">
							<Button size="small" onClick={() => editEvent(event)} icon={<EditOutlined />} />
						</Tooltip>

						<Tooltip title="Delete">
							<Popconfirm
								okText="Yes"
								cancelText="No"
								placement="left"
								onConfirm={() => deleteEvent(event.id)}
								title="Are you sure you want to delete this event?"
							>
								<Button size="small" danger icon={<DeleteOutlined />} />
							</Popconfirm>
						</Tooltip>
					</div>
				)}
			</div>
		</div>
	);
}
