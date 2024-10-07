import { message } from "antd";

export const useNotification = () => {
	const success = (msg?: string) => {
		if (!msg || msg.length == 0) msg = "Success!";
		return message.success(msg, 3);
	};

	const error = (msg?: string) => {
		if (!msg || msg.length == 0) msg = "An error occurred";
		return message.error(msg, 3);
	};

	const warning = (msg: string) => {
		return message.warning(msg, 3);
	};

	return { success, error, warning };
};
