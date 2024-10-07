import { CheckOutlined, CloseOutlined } from "@ant-design/icons";
import { Button, Drawer, Row, Space } from "antd";
import { ReactNode } from "react";

interface EditDrawerProps {
	title: string;
	children: ReactNode;
	visible: boolean;
	setVisible: (visible: boolean) => void;
	loading: boolean;
	handleOk: () => void;
	onClose?: () => void;
}

export default function RightDrawer({ title, children, visible, setVisible, loading, handleOk, onClose }: EditDrawerProps) {
	const handleClose = () => {
		if (onClose) onClose();
		setVisible(false);
	};

	return (
		<Drawer
			width={620}
			open={visible}
			destroyOnClose
			closeIcon={null}
			onClose={handleClose}
			title={
				<Row justify="space-between">
					<span style={{ fontSize: "16px" }}>{title}</span>
					<CloseOutlined onClick={handleClose} />
				</Row>
			}
			footer={
				<Space style={{ float: "right" }}>
					<Button disabled={loading} onClick={handleClose}>
						Cancel
					</Button>

					<Button disabled={loading} loading={loading} onClick={handleOk} icon={<CheckOutlined />} type="primary">
						Save
					</Button>
				</Space>
			}
		>
			{children}
		</Drawer>
	);
}
