import { PlusOutlined, SearchOutlined } from "@ant-design/icons";
import { Button, Col, Empty, Form, Input, Row, Skeleton, Spin } from "antd";
import { useContext, useEffect, useState } from "react";
import EventCard from "../card/eventCard";

import { EventContext } from "../context/EventContext";

import { IEvent } from "../../interfaces/Event";
import RightDrawer from "../partials/drawer";
import EventForm from "../partials/form";

import "./list.css";

export default function List() {
	const [form] = Form.useForm();

	const { events, loading, getEvents, setSearchTerm, deleteEvent, createEvent, editEvent } = useContext(EventContext);

	const [editingEvent, setEditingEvent] = useState<IEvent | null>(null);
	const [drawerVisible, setDrawerVisible] = useState(false);

	useEffect(() => {
		getEvents();
	}, []);

	const saveForm = async (event: IEvent) => {
		if (editingEvent) {
			event.id = editingEvent.id;

			const success = await editEvent(event);
			if (!success) return;

			setEditingEvent(null);
		} else {
			const success = await createEvent(event);
			if (!success) return;
		}

		setDrawerVisible(false);
		form.resetFields();
	};

	const onEditEvent = (event: IEvent) => {
		setEditingEvent(event);
		setDrawerVisible(true);
	};

	return (
		<Spin spinning={loading} className="list-spin">
			<div className="list-container">
				<Row gutter={[24, 18]} className="list-wrapper">
					<Col span={12}>
						<Input
							allowClear
							autoComplete="off"
							prefix={<SearchOutlined />}
							placeholder={"Search"}
							onChange={(e) => setSearchTerm(e.target.value)}
							disabled={loading}
						/>
					</Col>

					<Col span={12} style={{ float: "right" }}>
						<Button onClick={() => setDrawerVisible(true)} disabled={loading} type="primary" icon={<PlusOutlined />}>
							Create new event
						</Button>
					</Col>

					{loading && events.length === 0 && (
						<>
							{[...Array(3)].map((_, i) => (
								<Col span={24} key={i}>
									<Skeleton.Input active block={true} className="list-skeleton" />
								</Col>
							))}
						</>
					)}

					{events?.map((event) => (
						<Col span={24} key={event.id}>
							<EventCard event={event} editEvent={onEditEvent} deleteEvent={deleteEvent} />
						</Col>
					))}

					{!loading && events.length === 0 && (
						<Col span={24} style={{ marginTop: "24px" }}>
							<Empty image={Empty.PRESENTED_IMAGE_SIMPLE} style={{ margin: 0 }} description="No events to display" />
						</Col>
					)}
				</Row>
			</div>

			<RightDrawer
				title={editingEvent ? "Edit event" : "Create new event"}
				visible={drawerVisible}
				setVisible={setDrawerVisible}
				loading={loading}
				handleOk={() => form.submit()}
				onClose={() => {
					form.resetFields();
					setEditingEvent(null);
				}}
			>
				<EventForm form={form} event={editingEvent} saveForm={saveForm} />
			</RightDrawer>
		</Spin>
	);
}
