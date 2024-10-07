import { Col, DatePicker, Form, FormInstance, Input, InputNumber, Row, Select } from "antd";
import { useEffect } from "react";
import { EventStatus, IEvent } from "../../interfaces/Event";
import dayjs from "dayjs";

type EventFormProps = {
	event?: IEvent | null;
	form: FormInstance;
	saveForm: (event: IEvent) => void;
};

type EventFormValues = Omit<IEvent, "startDate" | "endDate"> & {
	startDate: Date;
	endDate: Date;
};

export const dateTimeFormat = "DD/MM/YYYY HH:mm";

export default function EventForm({ event, form, saveForm }: EventFormProps) {
	const statusOpt = [EventStatus.STARTED, EventStatus.COMPLETED, EventStatus.PAUSED];

	useEffect(() => {
		if (!event) return;

		form.setFieldsValue({
			title: event.title,

			startDate: dayjs(event.startDate, dateTimeFormat),
			endDate: dayjs(event.endDate, dateTimeFormat),
			price: event.price,
			status: event.status,
		});
	}, [event]);

	const handleFinish = (data: EventFormValues) => {
		const formattedValues: IEvent = {
			...data,
			startDate: dayjs(data.startDate).format(dateTimeFormat),
			endDate: dayjs(data.endDate).format(dateTimeFormat),
		};

		saveForm(formattedValues);
	};

	const requiredRules = [{ required: true, message: "Field is required" }];

	return (
		<Form
			form={form}
			layout="vertical"
			onFinish={handleFinish}
			initialValues={{
				["price"]: 1,
			}}
		>
			<Row gutter={[24, 24]}>
				<Col span={24}>
					<Form.Item name="title" label="Title" rules={requiredRules}>
						<Input type="text" placeholder="Title" />
					</Form.Item>
				</Col>

				<Col span={12}>
					<Form.Item name="price" label="Price" rules={requiredRules} valuePropName="value">
						<InputNumber addonAfter="$" min={1} controls={false} precision={2} style={{ width: "100%" }} />
					</Form.Item>
				</Col>

				<Col span={12}>
					<Form.Item name="status" label="Status" rules={requiredRules}>
						<Select filterOption={false} showSearch placeholder={"Select"}>
							{statusOpt.map((item, index) => (
								<Select.Option key={`${item}_${index}`} value={item} data={item}>
									{EventStatus[item]}
								</Select.Option>
							))}
						</Select>
					</Form.Item>
				</Col>

				<Col span={12}>
					<Form.Item name="startDate" label="Start date" rules={requiredRules}>
						<DatePicker style={{ width: "100%" }} showTime allowClear={false} format={dateTimeFormat} />
					</Form.Item>
				</Col>

				<Col span={12}>
					<Form.Item name="endDate" label="End date" rules={requiredRules}>
						<DatePicker style={{ width: "100%" }} showTime allowClear={false} format={dateTimeFormat} />
					</Form.Item>
				</Col>
			</Row>
		</Form>
	);
}
