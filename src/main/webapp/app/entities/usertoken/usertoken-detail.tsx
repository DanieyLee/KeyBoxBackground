import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './usertoken.reducer';
import { IUsertoken } from 'app/shared/model/usertoken.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUsertokenDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class UsertokenDetail extends React.Component<IUsertokenDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { usertokenEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="keyboxApp.usertoken.detail.title">Usertoken</Translate> [<b>{usertokenEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="userid">
                <Translate contentKey="keyboxApp.usertoken.userid">Userid</Translate>
              </span>
            </dt>
            <dd>{usertokenEntity.userid}</dd>
            <dt>
              <span id="state">
                <Translate contentKey="keyboxApp.usertoken.state">State</Translate>
              </span>
            </dt>
            <dd>{usertokenEntity.state}</dd>
            <dt>
              <span id="endDate">
                <Translate contentKey="keyboxApp.usertoken.endDate">End Date</Translate>
              </span>
            </dt>
            <dd>{usertokenEntity.endDate}</dd>
          </dl>
          <Button tag={Link} to="/entity/usertoken" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/entity/usertoken/${usertokenEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ usertoken }: IRootState) => ({
  usertokenEntity: usertoken.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(UsertokenDetail);
